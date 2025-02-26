package ch.sbb.polarion.extension.aad_synchronizer_fake_services;

import ch.sbb.polarion.extension.aad.synchronizer.service.PolarionService;
import com.polarion.alm.projects.IProjectService;
import com.polarion.platform.security.ISecurityService;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class FakePolarionService extends PolarionService {

    public static final Random RANDOM = new Random();

    private final List<String> polarionUsers;

    public FakePolarionService(ISecurityService securityService, IProjectService projectService, boolean dryRun, List<String> memberIds) {
        super(securityService, projectService, dryRun);

        polarionUsers = super.getAllPolarionUsers();

        for (String member : memberIds) {
            if (RANDOM.nextBoolean()) {
                if (member.startsWith("u")) {
                    polarionUsers.add(member.toUpperCase());
                } else {
                    polarionUsers.add(member);
                }
            }
        }

        if (RANDOM.nextBoolean()) {
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
