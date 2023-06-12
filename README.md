# builder-generator

Builder Generator for Kotlin data class.

# Install

- `git clone https://github.com/okaponta/builder-generator.git`
- `brew install kotlin`

# Usage

- `kotlin generator.kts -- -arg "path to your kotlin data file"`

# Recommended Usage

- add alias.
  - `alias ktgenerate='(){pushd /path/to/repo;kotlin generator.kts -- -arg $1 | pbcopy;popd}'`
- press `Command + Shift + C` to get your path to file
- enter `ktgenerate (paste path)`
- generated builder is now on your clipboard!!
