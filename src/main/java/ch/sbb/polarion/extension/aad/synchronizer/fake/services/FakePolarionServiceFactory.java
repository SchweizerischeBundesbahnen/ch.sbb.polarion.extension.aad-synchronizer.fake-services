package ch.sbb.polarion.extension.aad.synchronizer.fake.services;

import ch.sbb.polarion.extension.aad.synchronizer.service.IPolarionService;
import ch.sbb.polarion.extension.aad.synchronizer.service.IPolarionServiceFactory;
import com.polarion.alm.projects.IProjectService;
import com.polarion.platform.security.ISecurityService;

import java.util.List;

public class FakePolarionServiceFactory implements IPolarionServiceFactory {

    @Override
    public IPolarionService createPolarionService(ISecurityService securityService, IProjectService projectService, boolean dryRun, List<String> memberIds) {
        return new FakePolarionService(securityService, projectService, dryRun, memberIds);
    }
}
