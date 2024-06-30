package com.automation.etc_mapping.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.Arrays;
import java.util.List;

@Service
public class EtcMappingService {

    @Autowired
    private Session session;

    private static final List<String> ENVIRONMENTS = Arrays.asList("map", "map.dev", "map.qa", "map.stage", "map.uat");
    private static final List<String> PROTOCOLS = Arrays.asList("http", "https");

    public void createEtcMappings(String matchPattern, String redirectPattern) throws RepositoryException {
        Node root = session.getRootNode();

        for (String env : ENVIRONMENTS) {
            Node mapEnv;
            if (!root.hasNode("etc/" + env)) {
                mapEnv = root.addNode("etc/" + env, "sling:Folder");
            } else {
                mapEnv = root.getNode("etc/" + env);
            }

            for (String protocol : PROTOCOLS) {
                Node protocolNode;
                if (!mapEnv.hasNode(protocol)) {
                    protocolNode = mapEnv.addNode(protocol, "sling:Folder");
                } else {
                    protocolNode = mapEnv.getNode(protocol);
                }

                Node mappingNode;
                if (!protocolNode.hasNode("homepage")) {
                    mappingNode = protocolNode.addNode("homepage", "sling:Mapping");
                } else {
                    mappingNode = protocolNode.getNode("homepage");
                }

                mappingNode.setProperty("sling:match", matchPattern);
                mappingNode.setProperty("sling:internalRedirect", redirectPattern);
            }
        }

        session.save();
    }
}