# Targeted Deanonymization via the Cache Side Channel: Attacks and Defenses
Artifact for paper submission

## Demo page

You will find below an Online-Only attack page using the YouTube service which is prepared for Win-Chrome system in the paper draft
* [**Online-Only Attack Page**](https://leakuidatorplusteam.github.io/artifacts/wp_mse.html)

Note that the configurations in the source code of the attack page can be changed as follows to target Mac-M1-Chrome system in the paper draft:
- COUNT_SWEEPS = true
- CACHE_SETS = 4096
- SAMPLING_PERIOD_IN_MS = 10

## Python notebook

Our classification over collected cache traces can be viewed using the interactive Python notebook at the following link:
[https://colab.research.google.com/github/leakuidatorplusteam/artifacts/blob/main/cache_demo.ipynb](https://colab.research.google.com/github/leakuidatorplusteam/artifacts/blob/main/cache_demo.ipynb)

Each json file contains raw cache traces in target and non-target states for each setup, altogether in the dataset zip file at the root of this repository
