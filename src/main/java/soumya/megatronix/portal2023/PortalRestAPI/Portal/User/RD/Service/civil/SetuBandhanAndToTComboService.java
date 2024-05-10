package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.civil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Model.MrdModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.SetuBandhanAndToTComboModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.SetuBandhanModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.TrackOTeasureModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.civil.SetuBandhanAndToTComboRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SetuBandhanAndToTComboService {

  @Autowired
  private SetuBandhanAndToTComboRepository setuBandhanAndToTComboRepository;
  @Autowired
  private SetuBandhanService setuBandhan;
  @Autowired
  private TrackOTeasureService tot;

  @Async
  public CompletableFuture< SetuBandhanAndToTComboModel > saveComboCivil( SetuBandhanAndToTComboModel model ) {
    List<String> tid = new ArrayList<>();

    SetuBandhanModel setuBandhanModel = new SetuBandhanModel();
    setuBandhanModel.setGid1( model.getGid1() );
    setuBandhanModel.setGid2( model.getGid2() );
    setuBandhanModel.setGid3( model.getGid3() );
    setuBandhanModel.setNumber1( model.getNumber1() );
    CompletableFuture< SetuBandhanModel > setuBandhanModel1 = setuBandhan.setuBandhanRd( setuBandhanModel );

    tid.add( setuBandhanModel1.join().getTid() );

    TrackOTeasureModel totModel = new TrackOTeasureModel();
    totModel.setGid1( model.getGid1() );
    totModel.setGid2( model.getGid2() );
    totModel.setGid3( model.getGid3() );
    totModel.setNumber1( model.getNumber1() );
    CompletableFuture< TrackOTeasureModel > totModel1 = tot.trackOTreasureRd( totModel );

    tid.add( totModel1.join().getTid() );

    model.setTid(tid);
    return CompletableFuture.completedFuture( setuBandhanAndToTComboRepository.save( model ) );
  }

  @Async
  public CompletableFuture< MrdModel > checkGid (String gid) {
    CompletableFuture<MrdModel> setuBandhanModel = setuBandhan.checkGid(gid);
    CompletableFuture<MrdModel> totModel = tot.checkGid(gid);

    if ( setuBandhanModel.join().equals(totModel.join()) ) {
      return CompletableFuture.completedFuture( setuBandhanModel.join() );
    }
    return CompletableFuture.completedFuture( totModel.join() );
  }
}
