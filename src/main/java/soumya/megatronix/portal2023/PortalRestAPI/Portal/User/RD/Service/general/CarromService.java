package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Model.MrdModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Repository.MrdRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.electrical.Electrical2;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.Carrom;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.general.CarromRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Verification.Email.Service.EmailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CarromService {

    @Autowired
    private CarromRepository general;
    @Autowired
    private MrdRepository repo;
    @Autowired
    private EmailService emailService;

    @Async
    public CompletableFuture<Carrom> carromRd(Carrom member) {
        Optional<MrdModel> gid1 = repo.findByGid(member.getGid1());
        Optional<MrdModel> gid2 = Optional.ofNullable(member.getGid2()).flatMap(repo::findByGid);

        if (
                gid1.isPresent() &&
                        (gid2.isPresent() || member.getGid2() == null )
        ) {
            List<Carrom> list = general.findAll();
            for (Carrom i : list) {
                if (
                        (
                                member.getGid1().equals(i.getGid1()) ||
                                        member.getGid1().equals(i.getGid2()) ||
                                        member.getGid1().equals(member.getGid2())
                        )||
                                (
                                        (member.getGid2() != null &&member.getGid2().equals(i.getGid1())) ||
                                                (member.getGid2() != null &&member.getGid2().equals(i.getGid2()) )||

                                                (member.getGid2() != null &&member.getGid2().equals(member.getGid1()))
                                )
                ) {
                    throw new RuntimeException("GID already exists.");
                }
            }
            if(
                    (
                            member.getGid1().equals(member.getGid2())
                    )||
                            (
                                    member.getGid2() != null &&member.getGid2().equals(member.getGid1())
                            )
            ){
                throw new RuntimeException("GID already exists.");
            }
            else {
                CompletableFuture<Carrom> carrom = CompletableFuture.completedFuture(general.save(member));
                member.setTid("paridhi"+member.getId()+"2002"+member.getId()+"05202024");
                general.save(member);
                String tid = member.getTid();

                List<String> emails = getEmails(tid);
                emailService.sendEventRegistrationEmail(
                        tid,
                        "Carrom",
                        "Team",
                        emails.toArray(new String[0])
                );

                return carrom;
            }
        }
        throw new RuntimeException("GID not present");
    }

    @Async
    public CompletableFuture<MrdModel> checkGid(String gid){
        MrdModel gn=repo.getModelByGid(gid);
        if(gn!=null){
            for(Carrom i:general.findAll()){
                if (
                        gn.getGid().equals(i.getGid1()) ||
                                gn.getGid().equals(i.getGid2())
                ){
                    throw new RuntimeException("GID already exists.");
                }
            }

            return CompletableFuture.completedFuture(gn);
        }
        else {
            throw new RuntimeException("GID not present");
        }
    }

    public Optional<Carrom> findByGid(String gid) {
        Optional<Carrom> gid1 = general.findByGid1(gid);
        Optional<Carrom> gid2 = general.findByGid2(gid);

        if (gid1.isPresent() && !gid2.isPresent()) {
            return gid1;
        } else if (!gid1.isPresent() && gid2.isPresent()) {
            return gid2;
        } else {
            return Optional.empty();
        }
    }

    public List<String> getEmails (String tid) {
        Optional<Carrom> model = general.findByTid(tid);
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
