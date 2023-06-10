# cnj-monitoring-backend-micro

Cloud native MicroProfile backend with support of cluster monitoring.

## Status

![Build status](https://codebuild.eu-west-1.amazonaws.com/badges?uuid=eyJlbmNyeXB0ZWREYXRhIjoiSEJsMW1ZSTN1WXFPMGJhdllvN3pEWnJuK3dmQkdiWXFJM1FjUDlablNnRnRxQWwzdDFnUHUxQitsWDdSQ1oxeDQ3a0FIM1JlZzlmdlFTdHhlUy9mZEQ4PSIsIml2UGFyYW1ldGVyU3BlYyI6ImNMWnNEeS9GWFZYTHllWVMiLCJtYXRlcmlhbFNldFNlcmlhbCI6MX0%3D&branch=main)

## Release information

Check [changelog](changelog.md) for latest version and release information.

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
