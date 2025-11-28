package ch.sbb.polarion.extension.fake_services.rest.model.opentext;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Container {

    private String id;
    private String name;
    private String nodeId;
    private String folder;

}
