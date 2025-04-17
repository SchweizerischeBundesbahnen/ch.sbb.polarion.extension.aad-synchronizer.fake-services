package ch.sbb.polarion.extension.aad_synchronizer.fake_services.service;

import ch.sbb.polarion.extension.aad.synchronizer.service.PolarionService;
import com.polarion.alm.projects.IProjectService;
import com.polarion.platform.security.ISecurityService;
import org.jetbrains.annotations.NotNull;

import java.security.SecureRandom;
import java.util.List;

public class FakePolarionService extends PolarionService {

    private final List<String> polarionUsers;

    public FakePolarionService(ISecurityService securityService, IProjectService projectService, boolean dryRun, List<String> memberIds, SecureRandom random) {
        super(securityService, projectService, dryRun);
        polarionUsers = super.getAllPolarionUsers();

        for (String member : memberIds) {
            if (random.nextBoolean()) {
                if (member.startsWith("u")) {
                    polarionUsers.add(member.toUpperCase());
                } else {
                    polarionUsers.add(member);
                }
            }
        }

        if (random.nextBoolean()) {
            polarionUsers.add("testuser");
            polarionUsers.add("testUser");
            polarionUsers.add("TESTUSER");
        }
    }

    @Override
    protected @NotNull List<String> getAllPolarionUsers() {
        return polarionUsers;
    }
}
