# Contribution Guide

## Code of Conduct

Please read and follow our [Code of Conduct](https://github.com/scotiabank/accelerator-initializer/blob/master/CODE_OF_CONDUCT.md).

## Submitting Code

When submitting code:

**In order to help reviewers reason about the changes and implications of changes, try to submit small pull requests that represent a single feature or change.**

1. Write your code
   * When creating new API endpoints ensure they are documented to allow Swagger generation
   * TODO: Pick a style guide to follow

2. Write tests:
   * Ensure your code is covered by unit tests
      * Unit test coverage required: 80%
      * Unit test coverage target: 95%
   * Acceptance test strategy TBD

3. Ensure you can build
   ```
   gradlew clean assemble
   ```
   
4. Ensure all tests pass
   ```
   gradlew check
   ```
   
5. Submit a pull request with your changes
   * Give your pull request a descriptive title
   * Include a description of the changes you've made
      * Summarize the changes
      * Describe the implications of the changes
      * Include the intent of the change