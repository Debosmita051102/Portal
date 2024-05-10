package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.civil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Model.MrdModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Repository.MrdRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.SetuBandhanModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.civil.SetuBandhanRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Verification.Email.Service.EmailService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class SetuBandhanService {
    @Autowired
    private SetuBandhanRepository civil;

    @Autowired
    private MrdRepository repo;

    @Autowired
    private EmailService emailService;

    @Async
    public CompletableFuture<SetuBandhanModel> setuBandhanRd(SetuBandhanModel member) {
        if(member.getNumber1()!=null) {
            Optional<MrdModel> gid1 = repo.findByGid(member.getGid1());
            Optional<MrdModel> gid2 = Optional.ofNullable(member.getGid2()).flatMap(repo::findByGid);
            Optional<MrdModel> gid3 = Optional.ofNullable(member.getGid3()).flatMap(repo::findByGid);
            if (gid1.isPresent() &&
                    (gid2.isPresent() || member.getGid2() == null) &&
                    (gid3.isPresent() || member.getGid3() == null)
            ) {
                List<SetuBandhanModel> list = civil.findAll();
                for (SetuBandhanModel i : list) {
                    if (
                            (
                                    member.getGid1().equals(i.getGid1()) ||
                                            member.getGid1().equals(i.getGid2()) ||
                                            member.getGid1().equals(i.getGid3()) ||

                                            member.getGid1().equals(member.getGid2()) ||
                                            member.getGid1().equals(member.getGid3())
                            ) ||
                                    (
                                            (member.getGid2() != null && member.getGid2().equals(i.getGid1())) ||
                                                    (member.getGid2() != null && member.getGid2().equals(i.getGid2())) ||
                                                    (member.getGid2() != null && member.getGid2().equals(i.getGid3())) ||

                                                    (member.getGid2() != null && member.getGid2().equals(member.getGid1())) ||
                                                    (member.getGid2() != null && member.getGid2().equals(member.getGid3()))
                                    ) ||

                                    (
                                            (
                                                    (member.getGid3() != null && member.getGid3().equals(i.getGid3())) ||
                                                            (member.getGid3() != null && member.getGid3().equals(i.getGid1())) ||
                                                            (member.getGid3() != null && member.getGid3().equals(i.getGid2())) ||

                                                            (member.getGid3() != null && member.getGid3().equals(member.getGid1())) ||
                                                            (member.getGid3() != null && member.getGid3().equals(member.getGid2()))
                                            )
                                    )
                    ) {
                        throw new RuntimeException("GID already exists.");
                    }
                }
                if (
                        (
                                member.getGid1().equals(member.getGid2()) ||
                                        member.getGid1().equals(member.getGid3())
                        ) ||
                                (
                                        (member.getGid2() != null && member.getGid2().equals(member.getGid1())) ||
                                                (member.getGid2() != null && member.getGid2().equals(member.getGid3()))
                                ) ||
                                (
                                        (member.getGid3() != null && member.getGid3().equals(member.getGid2())) ||
                                                (member.getGid3() != null && member.getGid3().equals(member.getGid1()))
                                )
                )
                    throw new RuntimeException("GID already exists");
                else {
                    CompletableFuture<SetuBandhanModel> setuBandhan = CompletableFuture.completedFuture(civil.save(member));
                    member.setTid("paridhi" + member.getId() + "2002" + member.getId() + "05202024");
                    civil.save(member);
                    String tid = member.getTid();

                    List<String> emails = getEmails(tid);
                    emailService.sendEventRegistrationEmail(
                            tid,
                            "Setu-Bandhan",
                            "Team",
                            emails.toArray(new String[0])
                    );

                    return setuBandhan;
                }
            }
            throw new RuntimeException("GID not present");
        }
        else
            throw new RuntimeException("Number is required");
    }

    public CompletableFuture<MrdModel> checkGid(String gid) {
        MrdModel setuBandhan = repo.getModelByGid(gid);
        if (setuBandhan != null) {
            for (SetuBandhanModel i : civil.findAll()) {
                if (
                        setuBandhan.getGid().equals(i.getGid1()) ||
                                setuBandhan.getGid().equals(i.getGid2()) ||
                                setuBandhan.getGid().equals(i.getGid3())
                ) {
                    throw new RuntimeException("GID already exists.");
                }
            }

            return CompletableFuture.completedFuture(setuBandhan);
        } else {
            throw new RuntimeException("GID not present");
        }
    }

    public Optional<SetuBandhanModel> findByGid(String gid) {
        Optional<SetuBandhanModel> gid1 = civil.findByGid1(gid);
        Optional<SetuBandhanModel> gid2 = civil.findByGid2(gid);
        Optional<SetuBandhanModel> gid3 = civil.findByGid3(gid);

        if(gid1.isPresent() && !gid2.isPresent() && !gid3.isPresent() ) {
            return gid1;
        } else if(!gid1.isPresent() && gid2.isPresent() && !gid3.isPresent() ) {
            return gid2;
        } else if(!gid1.isPresent() && !gid2.isPresent() && gid3.isPresent() ) {
            return gid3;
        } else {
            return Optional.empty();
        }
    }

    @Async
    public List<String> getEmails (String tid) {
        Optional<SetuBandhanModel> model = civil.findByTid(tid);
        Optional<MrdModel> user1 = repo.findByGid(model.get().getGid1());
        Optional<MrdModel> user2 = repo.findByGid(model.get().getGid2());
        Optional<MrdModel> user3 = repo.findByGid(model.get().getGid3());
        List<String> emails = new ArrayList<>();
        if (user1.isPresent() && user1.get().getEmail() != null && !user1.get().getEmail().isEmpty()) {
            emails.add(user1.get().getEmail());
        }
        if (user2.isPresent() && user2.get().getEmail() != null && !user2.get().getEmail().isEmpty()) {
            emails.add(user2.get().getEmail());
        }
        if (user3.isPresent() && user3.get().getEmail() != null && !user3.get().getEmail().isEmpty()) {
            emails.add(user3.get().getEmail());
        }

        return emails;
    }
}