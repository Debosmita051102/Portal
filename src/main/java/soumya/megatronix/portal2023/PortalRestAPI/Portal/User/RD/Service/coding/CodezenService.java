package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.coding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Model.MrdModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Repository.MrdRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.CodeQuestModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.CodezenModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.coding.CodezenRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Verification.Email.Service.EmailService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CodezenService {

    @Autowired
    private CodezenRepository coding;
    @Autowired
    private MrdRepository repo;
    @Autowired
    private EmailService emailService;

    @Async
    public CompletableFuture<CodezenModel> CodezenRd(CodezenModel member) {
        if(member.getNumber1()!=null) {
            Optional<MrdModel> gid1 = repo.findByGid(member.getGid1());
            Optional<MrdModel> gid2 = Optional.ofNullable(member.getGid2()).flatMap(repo::findByGid);

            if (
                    gid1.isPresent() &&
                            (gid2.isPresent() || member.getGid2() == null)
            ) {
                List<CodezenModel> list = coding.findAll();
                for (CodezenModel i : list) {
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
                                        member.getGid2() != null && member.getGid2().equals(member.getGid1())
                                )
                ) {
                    throw new RuntimeException("GID already exists.");

                } else {
                    CompletableFuture<CodezenModel> codezen = CompletableFuture.completedFuture(coding.save(member));
                    member.setTid("paridhi" + member.getId() + "2002" + member.getId() + "05202024");
                    coding.save(member);
                    String tid = member.getTid();

                    List<String> emails = getEmails(tid);
                    emailService.sendEventRegistrationEmail(
                            tid,
                            "Codezen",
                            member.getTeamname(),
                            emails.toArray(new String[0])
                    );

                    return codezen;
                }
            }
            throw new RuntimeException("GID not present");
        }
        else
        {
            throw new RuntimeException("Number is required");
        }
    }

    @Async
    public CompletableFuture<MrdModel> checkGid(String gid){
        MrdModel cp=repo.getModelByGid(gid);
        if(cp!=null){
            for(CodezenModel i:coding.findAll()){
                if (
                        cp.getGid().equals(i.getGid1()) ||
                                cp.getGid().equals(i.getGid2())
                ){
                    throw new RuntimeException("GID already exists.");
                }
            }

            return CompletableFuture.completedFuture(cp);
        }
        else {
            throw new RuntimeException("GID not present");
        }
    }

    public Optional<CodezenModel> findByGid(String gid) {
        Optional<CodezenModel> gid1 = coding.findByGid1(gid);
        Optional<CodezenModel> gid2 = coding.findByGid2(gid);

        if (gid1.isPresent() && !gid2.isPresent()) {
            return gid1;
        } else if (!gid1.isPresent() && gid2.isPresent()) {
            return gid2;
        } else {
            return Optional.empty();
        }
    }

    public List<String> getEmails (String tid) {
        Optional<CodezenModel> model = coding.findByTid(tid);
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