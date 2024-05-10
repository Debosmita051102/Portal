package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.electrical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Model.MrdModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Repository.MrdRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.CodeQuestModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.electrical.ElectriQuest;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.electrical.ElectriQuestRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Verification.Email.Service.EmailService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ElectriQuestService {

    @Autowired
    private ElectriQuestRepository electrical;

    @Autowired
    private MrdRepository repo;

    @Autowired
    private EmailService emailService;

    @Async
    public CompletableFuture<ElectriQuest> ElectriQuestRd(ElectriQuest member) {
        if(member.getNumber1()!=null) {
            Optional<MrdModel> gid1 = repo.findByGid(member.getGid1());
            Optional<MrdModel> gid2 = Optional.ofNullable(member.getGid2()).flatMap(repo::findByGid);
            if (gid1.isPresent() &&
                    (gid2.isPresent() || member.getGid2() == null)
            ) {
                List<ElectriQuest> list = electrical.findAll();
                for (ElectriQuest i : list) {
                    if (
                            (
                                    member.getGid1().equals(i.getGid1()) ||
                                            member.getGid1().equals(i.getGid2()) ||

                                            member.getGid1().equals(member.getGid2())
                            ) ||
                                    (
                                            (member.getGid2() != null && member.getGid2().equals(i.getGid1())) ||
                                                    (member.getGid2() != null && member.getGid2().equals(i.getGid2())) ||

                                                    (member.getGid2() != null && member.getGid2().equals(member.getGid1()))
                                    )
                    ) {
                        throw new RuntimeException("GID already exists.");
                    }
                }
                if (
                        (
                                member.getGid1().equals(member.getGid2())
                        ) ||
                                (
                                        (member.getGid2() != null && member.getGid2().equals(member.getGid1()))
                                )
                )
                    throw new RuntimeException("GID already exists");
                else {
                    CompletableFuture<ElectriQuest> electriQuest = CompletableFuture.completedFuture(electrical.save(member));
                    member.setTid("paridhi" + member.getId() + "2002" + member.getId() + "05202024");
                    electrical.save(member);
                    String tid = member.getTid();

                    List<String> emails = getEmails(tid);
                    emailService.sendEventRegistrationEmail(
                            tid,
                            "Electri-Quest",
                            "Team",
                            emails.toArray(new String[0])
                    );

                    return electriQuest;
                }
            }
            throw new RuntimeException("GID not present");
        }
        else
            throw new RuntimeException("Number is null");
    }

    public CompletableFuture<MrdModel> checkGid(String gid) {
        MrdModel ElectriQuest = repo.getModelByGid(gid);
        if (ElectriQuest != null) {
            for (ElectriQuest i : electrical.findAll()) {
                if (
                        ElectriQuest.getGid().equals(i.getGid1()) ||
                                ElectriQuest.getGid().equals(i.getGid2())
                ) {
                    throw new RuntimeException("GID already exists.");
                }
            }

            return CompletableFuture.completedFuture(ElectriQuest);
        } else {
            throw new RuntimeException("GID not present");
        }
    }
    public Optional<ElectriQuest> findByGid(String gid) {
        Optional<ElectriQuest> gid1 = electrical.findByGid1(gid);
        Optional<ElectriQuest> gid2 = electrical.findByGid2(gid);

        if(gid1.isPresent() && !gid2.isPresent() ) {
            return gid1;
        } else if(!gid1.isPresent() && gid2.isPresent() ) {
            return gid2;
        } else {
            return Optional.empty();
        }
    }

    @Async
    public List<String> getEmails (String tid) {
        Optional<ElectriQuest> model = electrical.findByTid(tid);
        Optional<MrdModel> user1 = repo.findByGid(model.get().getGid1());
        Optional<MrdModel> user2 = repo.findByGid(model.get().getGid2());
        List<String> emails = new ArrayList<>();
        if (user1.isPresent() && user1.get().getEmail() != null && !user1.get().getEmail().isEmpty()) {
            emails.add(user1.get().getEmail());
        }
        if (user2.isPresent() && user2.get().getEmail() != null && !user2.get().getEmail().isEmpty()) {
            emails.add(user2.get().getEmail());
        }

        return emails;
    }
}
