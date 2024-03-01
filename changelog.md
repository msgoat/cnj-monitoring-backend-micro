# Changelog

All notable changes to `cnj-monitoring-backend-micro` will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [6.2.0] - 2024-03-01
### Added
- commit-stage builds produce Docker images for linux/amd64 and linux/arm64/v8 platforms now
- added JSON-B configuration class
### Changed
- upgraded Payara to version 6.2024.2
- simplified POM
- consolidated common dependencies
- consolidated common cloudtrain dependencies
- upgraded Java to version 21
- upgraded Maven plugins and dependencies
- consolidated POM with other showcases
- consolidated system tests with other showcases
- Docker images use Generational Z garbage collector by default
### Fixed
- improved test coverage measurement with Jacoco to include all coverage data in reports
- log messages of test runs are displayed correctly now after upgrade to SLF4J 2.0.9 and using ServiceLoader for log provider lookup
- application logs are actually written to console after (re-)adding dependency slf4j-jdk14

## [6.1.0] - 2023-11-14
### Added
- Tagging of git branch
### Changed
- Upgraded to helm-maven-plugin version 5.0.0
- Now a helm chart is packaged and pushed as an artifact during the commit-stage build
- Now the helm chart is pulled before deploying during the integration-test-stage build
- removed dependency on cnj-common-test-jakarta by switching to model based system tests
- added missing dependency on assertj for testing
- upgraded Payara version to 6.2023.10
- consolidated dependencies


## [6.0.0] - 2023-06-09
### Changed
- moved to new AWS CodeBuild build pipeline
- moved to new CloudTrain EKS cluster
- upgraded everything
- added docker-compose.yml to run the showcase locally

## [5.2.0] - 2022-08-12
### Changed
- consolidated application-specific prometheus metric names with other showcases

## [5.1.0] - 2021-11-11
### Added
### Changed
- re-release after repo split
