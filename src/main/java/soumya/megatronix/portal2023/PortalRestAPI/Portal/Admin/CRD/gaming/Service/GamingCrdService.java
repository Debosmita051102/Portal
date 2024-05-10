package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.gaming.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.BgmiLan;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.ValorantLan;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.PesLan;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.gaming.BgmiLanRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.gaming.ValorantLanRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.gaming.PesLanRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class GamingCrdService {

    //    gaming Repository
    @Autowired
    private BgmiLanRepository bgmiLanRepository;
    @Autowired
    private ValorantLanRepository valorantLanRepository;
    @Autowired
    private PesLanRepository pesLanRepository;

    public ArrayList<BgmiLan> getBgmiLanCrd () {
        ArrayList< BgmiLan > models = new ArrayList<>();
        for ( BgmiLan model : bgmiLanRepository.findAll() ) {
            if ( ! model.isPlayed() )
                models.add(model);
        }
        return models;
    }

    public ArrayList<ValorantLan> getValorantLanCrd () {
        ArrayList<ValorantLan> models = new ArrayList<>();
        for (ValorantLan model : valorantLanRepository.findAll()) {
            if(!model.isPlayed())
                models.add(model);
        }
        return models;
    }

    public ArrayList<PesLan> getPesLanCrd () {
        ArrayList<PesLan> models = new ArrayList<>();
        for (PesLan model : pesLanRepository.findAll()) {
            if(!model.isPlayed())
                models.add(model);
        }
        return models;
    }


    public BgmiLan updateBgmiLanCrd (
            String tid,
            boolean played
    ) {
        Optional<BgmiLan> model = bgmiLanRepository.findByTid(tid);
        if (model.isEmpty())
            throw new RuntimeException("No such Tid found");
        else {
            model.get().setPlayed(played);
            return bgmiLanRepository.save(model.get());
        }
    }

    public ValorantLan updateValorantLanCrd (
            String tid,
            boolean played
    ) {
        Optional<ValorantLan> model = valorantLanRepository.findByTid(tid);
        if (model.isEmpty())
            throw new RuntimeException("No such Tid found");
        else {
            model.get().setPlayed(played);
            return valorantLanRepository.save(model.get());
        }
    }

    public PesLan updatePesLanCrd (
            String tid,
            boolean played
    ) {
        Optional<PesLan> model = pesLanRepository.findByTid(tid);
        if (model.isEmpty())
            throw new RuntimeException("No such Tid found");
        else {
            model.get().setPlayed(played);
            return pesLanRepository.save(model.get());
        }
    }
}
