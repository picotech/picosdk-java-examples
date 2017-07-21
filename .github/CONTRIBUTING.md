# Contributing

We welcome contributions to the picosdk-java-examples repository. By contributing to this repository, you agree to abide by our [code of conduct](CODE_OF_CONDUCT.md).

## Steps to contribute

1. Fork, then clone the repository:

```
git clone https://github.com/YOUR-USERNAME/picosdk-java-examples.git
```

2. Create a new branch - specify a name in lowercase, using hyphens to link words e.g. `ps3000a-streaming-example`

3. Push to the new branch on your fork, and then submit a pull request.

We will then review the pull request.

## Pull request guidelines

* Include a header in each .java file indicating the filename, author name, date and description of the file if new, or change
* Include any Javadoc documentation for your classes in a javadoc folder
* If contributing a new file or project, ensure that it follows the directory and package structure in use e.g.
  * `com.picotech.picoscope.driver-name.jna` for PicoScope JNA examples
  * `com.picotech.picolog.driver-name.jna` for PicoLog JNA examples
* The JDK used to create the examples should match that in [README.md](../README.md)
* [Commit messages](https://chris.beams.io/posts/git-commit/#seven-rules) should clearly communicate the reason for the change