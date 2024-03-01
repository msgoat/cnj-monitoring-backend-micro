# cnj-monitoring-backend-micro

Cloud native MicroProfile backend with support of cluster monitoring.

The application is packaged as a multi-architecture docker image which supports the following platforms:
* linux/amd64
* linux/arm64/v8

## Synopsis

This showcase demonstrates
* how to enable exposure of monitoring data in Prometheus format
* how to enable scraping of monitoring data by Prometheus
* how to publish custom monitoring data with annotations

### Enable exposure of monitoring data in Prometheus format

Actually, exposure of monitoring data in Prometheus format is supported by any MicroProfile compliant
application server by default.

The Prometheus data are published via path `/metrics` via the HTTP endpoint at port `8080`.
If your application listens on localhost at port 8080 for HTTP requests, 
the Prometheus data can be retrieved via URL: `http://localhost:8080/metrics`.

### Enable scraping of monitoring data by Prometheus

Prometheus running on Kubernetes is capable of detecting applications exposing monitoring data
automatically, as long as they are marked correctly. 
Depending on the way Prometheus is installed on the Kubernetes cluster, 
your application needs to be are marked in two different ways:

* Prometheus Operator Installation (default): you must add a ServiceMonitor resource to your Helm chart
* Prometheus Basic Installation (legacy): you must add certain annotations to your Pod

#### Adding a ServiceMonitor to your Helm Chart

When using the Prometheus Operator Installation you need to add an additional resource - a so-called `ServiceMonitor` - to your deployment.

A concrete sample for a ServiceMonitor Helm template can be found in [src/main/helm/cnj-monitoring-backend-micro/templates/servicemonitor.yaml](src/main/helm/cnj-monitoring-backend-micro/templates/servicemonitor.yaml).
A concrete Kubernetes manifest for a ServiceMonitor will look like this:

```yaml
apiVersion: monitoring.coreos.com/v1
kind: ServiceMonitor
metadata:
  name: cnj-monitor-backend-micro-cnj-monitoring-backend-micro
  labels:
    helm.sh/chart: cnj-monitoring-backend-micro-6.2.0
    app.kubernetes.io/name: cnj-monitoring-backend-micro
    app.kubernetes.io/instance: cnj-monitor-backend-micro
    app.kubernetes.io/version: "6.2.0.LOCAL.12345678"
    app.kubernetes.io/managed-by: Helm
spec:
  endpoints:
    - port: http
      path: /metrics
  selector:
    matchLabels:
      app.kubernetes.io/name: cnj-monitoring-backend-micro
      app.kubernetes.io/instance: cnj-monitor-backend-micro
  namespaceSelector:
    matchNames:
      - cloudtrain
```
The `spec.endpoints` element specifies port and path of the metrics endpoint.
The `spec.selector` element attaches the `ServiceMonitor` to all pods of the application.

The Prometheus service will detect new ServiceMonitors and start to scrape the monitoring data automatically.

#### Annotating your Pod

When using a basic installation of Prometheus, you will need to annotate your Pod with Prometheus annotations:

| Annotation            | Example       | Description                  |
|-----------------------|-------------------|------------------------------|
| `prometheus.io/scrape` | "true" or "false" | Controls if Prometheus should consider this Pod for scraping. |     
| `prometheus.io/path`  | "/metrics"        | Path of the metrics endpoint. |
| `prometheus.io/port`   | "8080"            | Port number of the metrics endpoint. | 

The Prometheus service will detect new annotated Pods with `prometheus.io/scrape` set to `true` and start to scrape the monitoring data automatically.

## Status

![Build status](https://codebuild.eu-west-1.amazonaws.com/badges?uuid=eyJlbmNyeXB0ZWREYXRhIjoiSEJsMW1ZSTN1WXFPMGJhdllvN3pEWnJuK3dmQkdiWXFJM1FjUDlablNnRnRxQWwzdDFnUHUxQitsWDdSQ1oxeDQ3a0FIM1JlZzlmdlFTdHhlUy9mZEQ4PSIsIml2UGFyYW1ldGVyU3BlYyI6ImNMWnNEeS9GWFZYTHllWVMiLCJtYXRlcmlhbFNldFNlcmlhbCI6MX0%3D&branch=main)

## Release information

Check [changelog](changelog.md) for latest version and release information.

## Docker Pull Command

`docker pull docker.cloudtrain.aws.msgoat.eu/cloudtrain/cnj-monitoring-backend-micro`

## Helm Pull Command

`helm pull oci://docker.cloudtrain.aws.msgoat.eu/cloudtrain-charts/cnj-monitoring-backend-micro`

## HOW-TO build this application locally

If all prerequisites are met, just run the following Maven command in the project folder:

```shell 
mvn clean verify -P pre-commit-stage
```

Build results: a Docker image containing the showcase application.

## HOW-TO run this showcase locally

In order to run the whole showcase locally, just run the following docker commands in the project folder:

```shell 
docker compose up -d
docker compose logs -f 
```
The showcase application will be accessible via `http://localhost:38080`.

The prometheus telemetry data will be available at `http://localhost:38080/metrics`.

Press `Ctlr+c` to stop tailing the container logs and run the following docker command to stop the show case:

```shell 
docker compose down
```
