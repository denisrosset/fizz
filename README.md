## Fizzdoc

### Structure of this Fizzdoc template

This template builds using SBT, with the following sub-projects:

- `fizzbuzz` is the root project,
- `core` contains the Scala code files for the project,
- `docs` contains the website and the `tut` documentation. 

In the documentation below, we use the following placeholders:

- `NAME` is the name of the project (default: `fizzdoc`),
- `USER` is your GitHub user name,
- `ORG` is the name of the organization used in `build.sbt` (default: `org.fizzorg`),
- `REPO` is the name of the remote repository,
- `HASH` is an unique hash generated automatically by `sbt-site`/`sbt-ghpages`.

### 1. Install the required tools

Install `Jekyll` and `Pygments`. 

### 2. Create a local copy of Fizzdoc 

- create a new empty Github repository. Add the files from the Fizzdoc `master` branch to the
  `master` branch of your repository. I advise *against* forking the repository;
  thus, all your actions with Git will be explicit.
  
- change the `REPO` variable at the top of the `build.sbt` with the URL of your repository,

- check that the project compiles with `sbt compile`,

- check that the tutorials compile with `sbt tut`.

### 3. Create the `gh-pages` branch

Create the `gh-pages` branch and push it to GitHub, and checkout back to `master` afterwards:

```shell
git checkout --orphan gh-pages                # new local orphan branch
git rm --cached -r .                          # remove files from the index
git commit --allow-empty -m "Initial commit." # commit the empty state
git push origin gh-pages                      # push to GitHub
git checkout master
```

### 4. Local test

- try a local publication with `ghpages-synch-local`. If this command fails for whatever reason,
  clean the local `~/.sbt/ghpages/HASH/ORG/NAME` directory created during the aborted generation.

- once `ghpages-synch-local` succeeds, launch `jekyll -serve` from the `~/.sbt/ghpages/HASH/ORG/NAME`
  directory. Navigate to the local URL http://127.0.0.1:4000/fizzdoc .
  
Note: the preview task from `sbt-site` does not work with this template.

### 5. Online test

- run `sbt ghpages-push-site`.

- navigate to `http://USER.github.io/fizzdoc` .

### Copyright and License

Following the [Cats](http://typelevel.org/cats) license from which this project is
extracted:

All code is available to you under the MIT license, available at
http://opensource.org/licenses/mit-license.php and also in the
[COPYING](COPYING) file. The design is informed by many other
projects, in particular Scalaz.

Copyright the maintainers, 2015-2016.
