package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Model.MrdModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Repository.MrdRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.electrical.Electrical2;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.TableTennis;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.general.TableTennisRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Verification.Email.Service.EmailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class TableTennisService {

    @Autowired
    private TableTennisRepository general;
    
    @Autowired
    private MrdRepository repo;

    @Autowired
    private EmailService emailService;

    @Async
    public CompletableFuture<TableTennis> tableTennisRd(TableTennis member) {
        Optional<MrdModel> gid1 = repo.findByGid(member.getGid1());

        if (gid1.isPresent()) {
            List<TableTennis> list = general.findAll();
            for (TableTennis i : list) {
                if (member.getGid1().equals(i.getGid1())) {
                    throw new RuntimeException("GID already exists.");
                }
            }
            CompletableFuture<TableTennis> TableTennis = CompletableFuture.completedFuture(general.save(member));
            member.setTid("paridhi"+member.getId()+"2002"+member.getId()+"05202024");
            general.save(member);
            String tid = member.getTid();

            List<String> emails = getEmails(tid);
            emailService.sendEventRegistrationEmail(
                    tid,
                    "Table-Tennis",
                    "Team",
                    emails.toArray(new String[0])
            );

            return TableTennis;
        }
        throw new RuntimeException("GID not present");
    }

    @Async
    public CompletableFuture<MrdModel> checkGid(String gid){
        MrdModel gn=repo.getModelByGid(gid);
        if(gn!=null){
            for(TableTennis i:general.findAll()){
                if (gn.getGid().equals(i.getGid1())){
                    throw new RuntimeException("GID already exists.");
                }
            }

            return CompletableFuture.completedFuture(gn);
        }
        else {
            throw new RuntimeException("GID not present");
        }
    }

    public Optional<TableTennis> findByGid(String gid) {
        Optional<TableTennis> gid1 = general.findByGid1(gid);

        if (gid1.isPresent()) {
            return gid1;
        } else {
            return Optional.empty();
        }
    }

    public List<String> getEmails (String tid) {
        Optional<TableTennis> model = general.findByTid(tid);
        Optional<MrdModel> user1 = repo.findByGid(model.get().getGid1());
        List<String> emails = new ArrayList<>();
        if (user1.isPresent() && user1.get().getEmail() != null && !user1.get().getEmail().isEmpty()) {
            emails.add(user1.get().getEmail());
        }

        return emails;
    }
}
