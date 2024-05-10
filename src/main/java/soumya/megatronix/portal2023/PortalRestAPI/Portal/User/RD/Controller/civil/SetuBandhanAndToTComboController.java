package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Controller.civil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.SetuBandhanAndToTComboModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.civil.SetuBandhanAndToTComboService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/megatronix/paridhi/user/civil")
public class SetuBandhanAndToTComboController {

  @Autowired
  private SetuBandhanAndToTComboService service;

  @Async
  @PostMapping("/setu-tot-combo")
  public CompletableFuture< ResponseEntity<?> > handleSetuBandhanAndToTCombo(
      @RequestBody SetuBandhanAndToTComboModel model
  ) {
    return service.saveComboCivil(model)
      .thenApply( success -> {
        if ( success != null )
          return ResponseEntity.ok().body( success.getTid() );
        else
          return ResponseEntity.notFound().build();
      } )
      .exceptionally( ex -> ResponseEntity.badRequest().body( ex.getMessage() ) );
  }

  @Async
  @GetMapping("/setu-tot-combo/{gid}")
  public CompletableFuture< ResponseEntity<?> > handleCheckGidForSetuBandhanAndToTCombo(
      @PathVariable("gid") String gid
  ) {
    return service.checkGid(gid)
      .thenApply( success -> {
        if ( success != null )
          return ResponseEntity.ok().body( success );
        else
          return ResponseEntity.notFound().build();
      } )
      .exceptionally( ex -> ResponseEntity.badRequest().body( ex.getMessage() ) );
  }
}
