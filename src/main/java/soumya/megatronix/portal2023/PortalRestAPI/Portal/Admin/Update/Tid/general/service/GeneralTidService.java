package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.general.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.BingeQuiz;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.Carrom;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.TableTennis;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.general.BingeQuizRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.general.CarromRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.general.TableTennisRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.general.BingeQuizService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.general.CarromService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.general.TableTennisService;
import soumya.megatronix.portal2023.PortalRestAPI.Verification.Email.Service.EmailService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class GeneralTidService {

    @Autowired
    private BingeQuizRepository bingeQuizRepository;
    @Autowired
    private TableTennisRepository tableTennisRepository;
    @Autowired
    private CarromRepository carromRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private BingeQuizService bingeQuizService;
    @Autowired
    private TableTennisService tableTennisService;
    @Autowired
    private CarromService carromService;

    public BingeQuiz updateBingeQuizPaidStatus (
            String tid,
            boolean paid
    ) {
        Optional<BingeQuiz> model = bingeQuizRepository.findByTid(tid);

        if(model.isEmpty())
            throw new RuntimeException("No such TID found");
        else {
            model.get().setPaid(paid);

            List<String> emails = bingeQuizService.getEmails(tid);
            emailService.sendEventRegistrationUpdateEmail(
                    tid,
                    "Binge-Quiz",
                    "Team",
                    emails.toArray(new String[0])
            );

            return bingeQuizRepository.save(model.get());
        }
    }

    public TableTennis updateTableTennisPaidStatus (
            String tid,
            boolean paid
    ) {
        Optional<TableTennis> model = tableTennisRepository.findByTid(tid);

        if(model.isEmpty())
            throw new RuntimeException("No such TID found");
        else {
            model.get().setPaid(paid);

            List<String> emails = tableTennisService.getEmails(tid);
            emailService.sendEventRegistrationUpdateEmail(
                    tid,
                    "Table-Tennis",
                    "Team",
                    emails.toArray(new String[0])
            );

            return tableTennisRepository.save(model.get());
        }
    }

    public Carrom updateCarromPaidStatus (
            String tid,
            boolean paid
    ) {
        Optional<Carrom> model = carromRepository.findByTid(tid);

        if(model.isEmpty())
            throw new RuntimeException("No such TID found");
        else {
            model.get().setPaid(paid);

            List<String> emails = carromService.getEmails(tid);
            emailService.sendEventRegistrationUpdateEmail(
                    tid,
                    "Carrom",
                    "Team",
                    emails.toArray(new String[0])
            );

            return carromRepository.save(model.get());
        }
    }
}
