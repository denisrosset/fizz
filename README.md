## Fizzdoc

### Structure of this Fizzdoc template

This template builds using SBT, with the following sub-projects:

- `fizzbuzz` is the root project,
- `core` contains the Scala code files for the project,
- `docs` contains the website and the `tut` documentation. 

### First step: create your copy

- get a copy of the Fizzdoc master branch. I advise *against* forking the repository,
  so that all your actions with Git are explicit.
  
- create an empty Github repository. Populate the `master` branch with the Fizzdoc files.

- edit the `gitRemoteRepo` variable at the top of the `build.sbt` file,

- add an orphan `gh-pages` branch according to the [Github guide](https://help.github.com/articles/creating-project-pages-manually/). Go back immediately to the master branch: you never need to edit manually the `gh-pages` branch.

- run `compile` and `ghpages-synch-local`, which will ...

### Copyright and License

Following the [Cats](http://typelevel.org/cats) license from which this project is
extracted:

All code is available to you under the MIT license, available at
http://opensource.org/licenses/mit-license.php and also in the
[COPYING](COPYING) file. The design is informed by many other
projects, in particular Scalaz.

Copyright the maintainers, 2015-2016.
