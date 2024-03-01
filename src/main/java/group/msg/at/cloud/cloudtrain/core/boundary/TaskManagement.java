package group.msg.at.cloud.cloudtrain.core.boundary;

import group.msg.at.cloud.cloudtrain.adapter.persistence.jpa.repository.GenericRepository;
import group.msg.at.cloud.cloudtrain.core.control.UserPermissionVerifier;
import group.msg.at.cloud.cloudtrain.core.entity.Task;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import java.util.List;
import java.util.UUID;

import static group.msg.at.cloud.cloudtrain.MetricsConstants.*;

/**
 * Simple {@code Boundary} that manages {@code Task} entities.
 */
@Stateless
@RolesAllowed("CLOUDTRAIN_USER")
public class TaskManagement {

    @Inject
    private GenericRepository repository;

    @Inject
    private UserPermissionVerifier verifier;

    @Counted(name = BUSINESS_OPERATION_METRIC_COUNT_NAME,
            description = BUSINESS_OPERATION_METRIC_COUNT_DESCRIPTION,
            absolute = true,
            tags = {BUSINESS_OPERATION_NAME_TAG + "=task_add"})
    @Timed(name = BUSINESS_OPERATION_METRIC_TIME_NAME,
            description = BUSINESS_OPERATION_METRIC_TIME_DESCRIPTION,
            absolute = true,
            tags = {BUSINESS_OPERATION_NAME_TAG + "=task_add"},
            unit = "seconds")
    @NotNull
    public UUID addTask(@NotNull @Valid Task newTask) {
        verifier.requirePermission("TASK_CREATE");
        newTask.setId(UUID.randomUUID());
        this.repository.addEntity(newTask);
        return newTask.getId();
    }

    @Counted(name = BUSINESS_OPERATION_METRIC_COUNT_NAME,
            description = BUSINESS_OPERATION_METRIC_COUNT_DESCRIPTION,
            absolute = true,
            tags = {BUSINESS_OPERATION_NAME_TAG + "=task_modify"})
    @Timed(name = BUSINESS_OPERATION_METRIC_TIME_NAME,
            description = BUSINESS_OPERATION_METRIC_TIME_DESCRIPTION,
            absolute = true,
            tags = {BUSINESS_OPERATION_NAME_TAG + "=task_modify"},
            unit = "seconds")
    public void modifyTask(@NotNull @Valid Task modifiedTask) {
        verifier.requirePermission("TASK_UPDATE");
        this.repository.setEntity(modifiedTask);
    }

    @Counted(name = BUSINESS_OPERATION_METRIC_COUNT_NAME,
            description = BUSINESS_OPERATION_METRIC_COUNT_DESCRIPTION,
            absolute = true,
            tags = {BUSINESS_OPERATION_NAME_TAG + "=task_get"})
    @Timed(name = BUSINESS_OPERATION_METRIC_TIME_NAME,
            description = BUSINESS_OPERATION_METRIC_TIME_DESCRIPTION,
            absolute = true,
            tags = {BUSINESS_OPERATION_NAME_TAG + "=task_get"},
            unit = "seconds")
    public Task getTaskById(@NotNull UUID taskId) {
        verifier.requirePermission("TASK_READ");
        return this.repository.getEntityById(Task.class, taskId);
    }

    @Counted(name = BUSINESS_OPERATION_METRIC_COUNT_NAME,
            description = BUSINESS_OPERATION_METRIC_COUNT_DESCRIPTION,
            absolute = true,
            tags = {BUSINESS_OPERATION_NAME_TAG + "=task_delete"})
    @Timed(name = BUSINESS_OPERATION_METRIC_TIME_NAME,
            description = BUSINESS_OPERATION_METRIC_TIME_DESCRIPTION,
            absolute = true,
            tags = {BUSINESS_OPERATION_NAME_TAG + "=task_delete"},
            unit = "seconds")
    public void removeTask(@NotNull UUID taskId) {
        verifier.requirePermission("TASK_DELETE");
        this.repository.removeEntityById(Task.class, taskId);
    }

    @Counted(name = BUSINESS_OPERATION_METRIC_COUNT_NAME,
            description = BUSINESS_OPERATION_METRIC_COUNT_DESCRIPTION,
            absolute = true,
            tags = {BUSINESS_OPERATION_NAME_TAG + "=task_browse"})
    @Timed(name = BUSINESS_OPERATION_METRIC_TIME_NAME,
            description = BUSINESS_OPERATION_METRIC_TIME_DESCRIPTION,
            absolute = true,
            tags = {BUSINESS_OPERATION_NAME_TAG + "=task_browse"},
            unit = "seconds")
    @NotNull
    public List<Task> getAllTasks() {
        verifier.requirePermission("TASK_READ");
        return this.repository.queryEntities(Task.class, Task.QUERY_ALL, null);
    }
}
