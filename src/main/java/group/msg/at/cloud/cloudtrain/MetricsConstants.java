package group.msg.at.cloud.cloudtrain;

/**
 * {@code Constants} for support of Microprofile's Metrics annotations.
 */
public interface MetricsConstants {

    /**
     * Name prefix for all metrics related to counters of business operation invocations.
     */
    String BUSINESS_OPERATION_METRIC_COUNT_NAME = "business_operation";

    /**
     * Name prefix for all metrics related to durations of business operation invocations.
     */
    String BUSINESS_OPERATION_METRIC_TIME_NAME = "business_operation_duration";

    /**
     * Description of all metrics related to counters of business operation invocations.
     */
    String BUSINESS_OPERATION_METRIC_COUNT_DESCRIPTION = "Number of invocations of this business operation";

    /**
     * Description of all metrics related to duration of business operation invocations.
     */
    String BUSINESS_OPERATION_METRIC_TIME_DESCRIPTION = "Duration of the invocation of this business operation in seconds";

    /**
     * Extra metric tag specifying the business operation's name.
     */
    String BUSINESS_OPERATION_NAME_TAG = "operation";
}
