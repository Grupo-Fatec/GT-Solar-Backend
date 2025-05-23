package org.github.gabrielgodoi.gtsolarbackend.services.mappers;

import org.github.gabrielgodoi.gtsolarbackend.entities.Admin;
import org.github.gabrielgodoi.gtsolarbackend.entities.Project;

public class CommonMapperUtils {
    public static String mapProject(Project project) {
        return project != null ? project.getId() : null;
    }

    public static String mapAdmin(Admin admin) {
        return admin != null ? admin.getName() : null;
    }
}
