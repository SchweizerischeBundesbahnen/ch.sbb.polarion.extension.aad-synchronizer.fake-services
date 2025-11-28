package ch.sbb.polarion.extension.fake_services.rest;

import ch.sbb.polarion.extension.fake_services.rest.controller.OpenTextApiController;
import ch.sbb.polarion.extension.generic.rest.GenericRestApplication;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class FakeServicesRestApplication extends GenericRestApplication {

    @Override
    protected @NotNull Set<Object> getExtensionControllerSingletons() {
        return Set.of(
                new OpenTextApiController()
        );
    }

}
