For historical purposes.

Look at [microsites](https://47deg.github.io/sbt-microsites/) instead.
## Fizz

An opinionated Scala project template to publish Github pages including:

- API documentation using [sbt-unidoc](https://github.com/sbt/sbt-unidoc),
- type-checked tutorials using [tut](https://github.com/tpolecat/tut),
- documentation string tests using [sbt-doctest](https://github.com/tkawachi/sbt-doctest),

automatically published using [sbt-site](https://github.com/sbt/sbt-site) and
[sbt-ghpages](https://github.com/sbt/sbt-ghpages).

Have a look at the [generated site](http://denisrosset.github.io/fizz/index.html).

For single project builds, use the [single-project template](http://github.com/denisrosset/fizzmono).

### Structure of this Fizz documentation template

This template builds using SBT, with the following sub-projects:

- `fizz` is the root project,
- `core` contains the Scala code files for the project,
- `docs` contains the website and the `tut` documentation.

In the documentation below, we use the following placeholders:

- `USER` is your GitHub user name,
- `REPO` is the name of the Github repository (default: `fizz`),
- `NAME` is the name of the Scala project (default: `fizz`),
- `ORG` is the organization name specified in `build.sbt` (default: `org.fizzorg`).

### 1. Install the required tools

Install a local version of [Jekyll](https://jekyllrb.com/) and [Graphviz](http://www.graphviz.org/).

### 2. Create a local copy of Fizz

- create a new empty Github repository named `fizz` (will make your life easier at first),

- add the files from the Fizz `master` branch to the `master` branch of your repository. 
  Do not fork the repository; that way, all your actions with Git will be explicit.
  Part of the SBT magic involved here will manipulate your Git repository.

- change the `USER` variable at the top of the `build.sbt` with your Github username,

- update the Jekyll configuration in the `docs/src/site/_config.yml` file,

- check that the project compiles with `sbt compile`,

- check that the tests run, including doctests, with `sbt test`,

- check that the tutorials compile with `sbt tut`.

- check that the documentation is generated with `sbt unidoc`.

- push your master branch to Github.

### 3. Create the `gh-pages` branch

Following the instructions below, create an empty `gh-pages` branch and push it to GitHub.
Checkout back immediately to `master` afterwards:

```shell
git checkout --orphan gh-pages                # new local orphan branch
git rm --cached -r .                          # remove files from the index
git commit --allow-empty -m "Initial commit." # commit the empty state
git push origin gh-pages                      # push to GitHub
git checkout master
```

### 4. Local test

- try a local publication with `sbt ghpages-synch-local`. A temporary directory will be created under
  `~/.sbt/ghpages/HASH/ORG/NAME`, where `HASH` is an hexadecimal hash. If this command fails for whatever reason,
  clean this directory to restart the process from a clean state.

- once `ghpages-synch-local` succeeds, launch `jekyll -serve` from the `~/.sbt/ghpages/HASH/ORG/NAME`
  directory. Navigate to the local URL http://127.0.0.1:4000/fizz .
  
Note: the preview task from `sbt-site` will not work with the current configuration. If you find a
better way, please tell me.

### 5. Online test

- run `sbt ghpages-push-site`.

- navigate to `http://USER.github.io/REPO` .

### Copyright and License

Following the [Cats](http://typelevel.org/cats) license from which this project is
extracted:

All code is available to you under the MIT license, available at
http://opensource.org/licenses/mit-license.php and also in the
[COPYING](COPYING) file. The design is informed by many other
projects, in particular Scalaz.

Copyright the maintainers, 2015-2016.
