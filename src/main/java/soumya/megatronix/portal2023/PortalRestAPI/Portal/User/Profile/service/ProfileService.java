package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.Profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Model.MrdModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Repository.MrdRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.Profile.model.ProfileModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.Profile.repository.profilerepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.MegaArchModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.SetuBandhanModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.TrackOTeasureModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.CodeQuestModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.CodezenModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.WebMindsModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.electrical.ElectriQuest;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.electrical.Electrical2;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.BgmiLan;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.ValorantLan;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.PesLan;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.BingeQuiz;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.Carrom;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.TableTennis;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.civil.MegaArchService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.civil.SetuBandhanService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.civil.TrackOTeasureService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.coding.CodeQuestService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.coding.CodezenService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.coding.WebMindsService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.electrical.ElectriQuestService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.electrical.Electrical2Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.gaming.BgmiLanService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.gaming.ValorantLanService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.gaming.PesLanService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.general.BingeQuizService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.general.CarromService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.general.TableTennisService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.robotics.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Async
public class ProfileService {
    @Autowired
    private profilerepository profileRepository;

    @Autowired
    private MrdRepository mrdRepository;

    @Autowired
    private MegaArchService megaArchService;
    @Autowired
    private SetuBandhanService setuBandhanService;
    @Autowired
    private TrackOTeasureService trackoteasureService;

    @Autowired
    private CodezenService codezenService;
    @Autowired
    private CodeQuestService codeQuestService;
    @Autowired
    private WebMindsService webMindsService;

    @Autowired
    private ElectriQuestService electriQuestService;
    @Autowired
    private Electrical2Service electrical2Service;

    @Autowired
    private BgmiLanService bgmiLanService;
    @Autowired
    private ValorantLanService valorantLanService;
    @Autowired
    private PesLanService pesLanService;

    @Autowired
    private BingeQuizService bingeQuizService;
    @Autowired
    private TableTennisService tableTennisService;
    @Autowired
    private CarromService carromService;

    @Autowired
    private LineTrekkerService lineTrekkerService;
    @Autowired
    private TriathlonService triathlonService;
    @Autowired
    private RoboKlassikerService roboKlassikerService;
    @Autowired
    private War8KgService roboWar8kgService;
    @Autowired
    private War15KgService roboWar15kgService;


    @Async
    public CompletableFuture<List<ProfileModel>> findProfile(String email)throws Exception{
        List<ProfileModel> profileModels = new ArrayList<>();
        Optional<List<MrdModel>> mrdModels = mrdRepository.findAllByEmail(email);
        if (mrdModels.isEmpty())
            throw new Exception("Invalid email");


        for(MrdModel m : mrdModels.get()) {
            Optional<MrdModel> mrdModel = mrdRepository.findByGid(m.getGid());
            if (mrdModel.isEmpty()){
                throw new Exception("Invalid gid");
            }
            ProfileModel profile = profileRepository.findByGid(m.getGid());
            if (profile == null) {
                CompletableFuture<ProfileModel> newProfile = addToProfile(m.getGid());
                profileRepository.save(newProfile.join());
                profileModels.add(newProfile.get());


            } else {
                CompletableFuture<ProfileModel> newProfile = addToProfile(m.getGid());

                profile.setMegaArchTid(newProfile.get().getMegaArchTid());
                if(newProfile.get().getMegaArchTid()!=null)
                    profile.setMegaArchPaid(newProfile.get().isMegaArchPaid());

                profile.setSetuBandhanTid(newProfile.get().getSetuBandhanTid());
                if ( newProfile.get().getSetuBandhanTid()!=null )
                    profile.setSetuBandhanPaid(newProfile.get().isSetuBandhanPaid());

                profile.setTrackoteasureTid(newProfile.get().getTrackoteasureTid());
                if ( newProfile.get().getTrackoteasureTid()!=null )
                    profile.setTrackoteasurePaid(newProfile.get().isTrackoteasurePaid());

                profile.setCodezenTid(newProfile.get().getCodezenTid());
                if ( newProfile.get().getCodezenTid()!=null )
                    profile.setCodezenPaid(newProfile.get().isCodezenPaid());

                profile.setCodeQuestTid(newProfile.get().getCodeQuestTid());
                if ( newProfile.get().getCodeQuestTid()!=null )
                    profile.setCodeQuestPaid(newProfile.get().isCodeQuestPaid());

                profile.setWebMindsTid(newProfile.get().getWebMindsTid());
                if ( newProfile.get().getWebMindsTid()!=null )
                    profile.setWebMindsPaid(newProfile.get().isWebMindsPaid());

                profile.setElectriQuestTid(newProfile.get().getElectriQuestTid());
                if ( newProfile.get().getElectriQuestTid()!=null )
                    profile.setElectriQuestPaid(newProfile.get().isElectriQuestPaid());

                profile.setElectrical2Tid(newProfile.get().getElectrical2Tid());
                if ( newProfile.get().getElectrical2Tid()!=null )
                    profile.setElectrical2Paid(newProfile.get().isElectrical2Paid());

                profile.setBgmiLanTid(newProfile.get().getBgmiLanTid());
                if ( newProfile.get().getBgmiLanTid()!=null )
                    profile.setBgmiLanPaid(newProfile.get().isBgmiLanPaid());

                profile.setValorantLanTid(newProfile.get().getValorantLanTid());
                if ( newProfile.get().getValorantLanTid()!=null )
                    profile.setValorantLanPaid(newProfile.get().isValorantLanPaid());

                profile.setPesLanTid(newProfile.get().getPesLanTid());
                if ( newProfile.get().getPesLanTid()!=null )
                    profile.setPesLanPaid(newProfile.get().isPesLanPaid());

                profile.setBingeQuizTid(newProfile.get().getBingeQuizTid());
                if ( newProfile.get().getBingeQuizTid()!=null )
                    profile.setBingeQuizPaid(newProfile.get().isBingeQuizPaid());

                profile.setTableTennisTid(newProfile.get().getTableTennisTid());
                if ( newProfile.get().getTableTennisTid()!=null )
                    profile.setTableTennisPaid(newProfile.get().isTableTennisPaid());

                profile.setCarromTid(newProfile.get().getCarromTid());
                if ( newProfile.get().getCarromTid()!=null )
                    profile.setCarromPaid(newProfile.get().isCarromPaid());

                profile.setLineTrekkerTid(newProfile.get().getLineTrekkerTid());
                if ( newProfile.get().getLineTrekkerTid()!=null )
                    profile.setLineTrekkerPaid(newProfile.get().isLineTrekkerPaid());

                profile.setTriathlonTid(newProfile.get().getTriathlonTid());
                if ( newProfile.get().getTriathlonTid()!=null )
                    profile.setTriathlonPaid(newProfile.get().isTriathlonPaid());

                profile.setRoboKlassikerTid(newProfile.get().getRoboKlassikerTid());
                if ( newProfile.get().getRoboKlassikerTid()!=null )
                    profile.setRoboKlassikerPaid(newProfile.get().isRoboKlassikerPaid());

                profile.setRoboWar8kgTid(newProfile.get().getRoboWar8kgTid());
                if ( newProfile.get().getRoboWar8kgTid()!=null )
                    profile.setRoboWar8kgPaid(newProfile.get().isRoboWar8kgPaid());

                profile.setRoboWar15kgTid(newProfile.get().getRoboWar15kgTid());
                if ( newProfile.get().getRoboWar15kgTid()!=null )
                    profile.setRoboWar15kgPaid(newProfile.get().isRoboWar15kgPaid());

                profileRepository.save(profile);
                profileModels.add(profile);
            }
        }

        return CompletableFuture.completedFuture(profileModels);
    }

    @Async
    public CompletableFuture<ProfileModel> addToProfile(String gid){
        Optional<MrdModel> optionalMrdModel = mrdRepository.findByGid(gid);

        if(optionalMrdModel.isPresent()) {
            MrdModel mrdModel = optionalMrdModel.get();
            ProfileModel profile=new ProfileModel();

            //mrdmodel
            profile.setName(mrdModel.getName());
            profile.setCollege(mrdModel.getCollege());
            profile.setYear(mrdModel.getYear());
            profile.setDepartment(mrdModel.getDepartment());
            profile.setRoll(mrdModel.getRoll());
            profile.setEmail(mrdModel.getEmail());
            profile.setPhoneNumber(mrdModel.getPhoneNumber());
            profile.setGid(mrdModel.getGid());
            profile.setPaid(mrdModel.isPaid());

            //civil
            Optional<MegaArchModel> megaArchServiceOptional = megaArchService.findByGid(gid);
            if(megaArchServiceOptional.isPresent()) {
                profile.setMegaArchTid(megaArchServiceOptional.get().getTid());
            } else {
                profile.setMegaArchTid(null);
            }

            Optional<SetuBandhanModel> setuBandhanServiceOptional = setuBandhanService.findByGid(gid);
            if(setuBandhanServiceOptional.isPresent()) {
                profile.setSetuBandhanTid(setuBandhanServiceOptional.get().getTid());
            } else {
                profile.setSetuBandhanTid(null);
            }

            Optional<TrackOTeasureModel> trackoteasureServiceOptional = trackoteasureService.findByGid(gid);
            if(trackoteasureServiceOptional.isPresent()) {
                profile.setTrackoteasureTid(trackoteasureServiceOptional.get().getTid());
            } else {
                profile.setTrackoteasureTid(null);
            }

            //coding
            Optional<CodezenModel> CodezenServiceOptional = codezenService.findByGid(gid);
            if(CodezenServiceOptional.isPresent()) {
                profile.setCodezenTid(CodezenServiceOptional.get().getTid());
            } else {
                profile.setCodezenTid(null);
            }

            Optional<CodeQuestModel> CodeQuestServiceOptional = codeQuestService.findByGid(gid);
            if(CodeQuestServiceOptional.isPresent()) {
                profile.setCodeQuestTid(CodeQuestServiceOptional.get().getTid());
            } else {
                profile.setCodeQuestTid(null);
            }

            Optional<WebMindsModel> WebMindsMindsServiceOptional = webMindsService.findByGid(gid);
            if(WebMindsMindsServiceOptional.isPresent()) {
                profile.setWebMindsTid(WebMindsMindsServiceOptional.get().getTid());
            } else {
                profile.setWebMindsTid(null);
            }

            //electrical
            Optional<ElectriQuest> ElectriQuestServiceOptional = electriQuestService.findByGid(gid);
            if(ElectriQuestServiceOptional.isPresent()) {
                profile.setElectriQuestTid(ElectriQuestServiceOptional.get().getTid());
            } else {
                profile.setElectriQuestTid(null);
            }

            Optional<Electrical2> electrical2ServiceOptional = electrical2Service.findByGid(gid);
            if(electrical2ServiceOptional.isPresent()) {
                profile.setElectrical2Tid(electrical2ServiceOptional.get().getTid());
            } else {
                profile.setElectrical2Tid(null);
            }

            //gaming
            Optional<BgmiLan> bgmiLanServiceOptional = bgmiLanService.findByGid(gid);
            if(bgmiLanServiceOptional.isPresent()) {
                profile.setBgmiLanTid(bgmiLanServiceOptional.get().getTid());
            } else {
                profile.setBgmiLanTid(null);
            }

            Optional<ValorantLan> valorantLanServiceOptional = valorantLanService.findByGid(gid);
            if(valorantLanServiceOptional.isPresent()) {
                profile.setValorantLanTid(valorantLanServiceOptional.get().getTid());
            } else {
                profile.setValorantLanTid(null);
            }

            Optional<PesLan> pesLanServiceOptional = pesLanService.findByGid(gid);
            if(pesLanServiceOptional.isPresent()) {
                profile.setPesLanTid(pesLanServiceOptional.get().getTid());
            } else {
                profile.setPesLanTid(null);
            }

            //general
            Optional<BingeQuiz> bingeQuizOptional = bingeQuizService.findByGid(gid);
            if(bingeQuizOptional.isPresent())
                profile.setBingeQuizTid(bingeQuizOptional.get().getTid());
            else
                profile.setBingeQuizTid(null);

            Optional<TableTennis> tableTennisOptional = tableTennisService.findByGid(gid);
            if(tableTennisOptional.isPresent())
                profile.setTableTennisTid(tableTennisOptional.get().getTid());
            else
                profile.setTableTennisTid(null);

            Optional<Carrom> carromOptional = carromService.findByGid(gid);
            if(carromOptional.isPresent())
                profile.setCarromTid(carromOptional.get().getTid());
            else
                profile.setCarromTid(null);

            //robotics
            Optional<LineTrekkerModel> LineTrekkerServiceOptional = lineTrekkerService.findByGid(gid);
            if(LineTrekkerServiceOptional.isPresent()) {
                profile.setLineTrekkerTid(LineTrekkerServiceOptional.get().getTid());
            } else {
                profile.setLineTrekkerTid(null);
            }

            Optional<TriathlonModel> TriathlonServiceOptional = triathlonService.findByGid(gid);
            if (TriathlonServiceOptional.isPresent()) {
                profile.setTriathlonTid(TriathlonServiceOptional.get().getTid());
            } else {
                profile.setTriathlonTid(null);
            }

            Optional<RoboKlassikerModel> RoboKlassikerServiceOptional = roboKlassikerService.findByGid(gid);
            if (RoboKlassikerServiceOptional.isPresent()) {
                profile.setRoboKlassikerTid(RoboKlassikerServiceOptional.get().getTid());
            } else {
                profile.setRoboKlassikerTid(null);
            }

            Optional<War8KgModel> roboWar8kgServiceOptional = roboWar8kgService.findByGid(gid);
            if (roboWar8kgServiceOptional.isPresent()) {
                profile.setRoboWar8kgTid(roboWar8kgServiceOptional.get().getTid());
            } else {
                profile.setRoboWar8kgTid(null);
            }

            Optional<War15KgModel> roboWar15kgServiceOptional = roboWar15kgService.findByGid(gid);
            if (roboWar15kgServiceOptional.isPresent()) {
                profile.setRoboWar15kgTid(roboWar15kgServiceOptional.get().getTid());
            } else {
                profile.setRoboWar15kgTid(null);
            }

            return CompletableFuture.completedFuture(profile);
        }
        else
            return null;
    }


}