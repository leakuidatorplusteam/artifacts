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

## USENIX Security '22 Artifact Evaluation

Instructions for artifact evaluation:

1. Open https://colab.research.google.com/
2. Upload the USENIX_Artifact_Evaluation/cache_demo.ipynb file and open it in Google Colab
3. On the left side of Google Colab interface, click on “Files”, then “Upload to session storage”, and choose the dataset.zip file from your local copy of this repository.
4. From the menu on top of Google Colab interface, click on “Runtime”, then “Run all”, and wait until it is finished

After finishing these 4 steps, the reported results are as follows:
- code block with comment starting with "## [Single Target Attacks]" shows the prediction accuracy on the dataset using LR (logistic regression classifier), MSE (mean squared error), and FastDTW (fast dynamic time warping). These results correspond to the results reported on table 1 and  4
- code block with the comment starting with "## [Chrome Android]" shows the results for experiments with Android Chrome
- code block with the comment starting with "## [Old Defense (Leakuidator)]" shows the results for experiments with Old defense prior to the modifications suggested in this paper
- code block with the comment starting with "### [Multi Target Attacks]" shows the results for experiments with multi target attacks, reported in table 2 of the paper.
- code block with the comment starting with "## [Average and Attack Accuracy plots]" correspond to figure 5 of the paper.

To get a sense of reproducibility of the attack, reviewers can collect traces using one of the three systems Win-Chrome, Mac-Intel-Safari, or Mac-M1-Chrome detailed in table 4 of the paper, using the respective attack pages at USENIX_Artifact_Evaluation directory. To customize the targeted deanonymization attack demo for a target user of your choice, do the following:
1. Login to the attacker Youtube account (e.g., attacker@gmail.com) at youtube.com
2. Upload a private video of at least 1 second duration in the attacker Youtube account. [Here](https://www.google.com/url?q=https://en.wikipedia.org/wiki/File:2001_space_travel.ogv&source=gmail-html&ust=1655925203656000&usg=AOvVaw32MUA7BVXqD87gM3gLj0Y0) is an example video you can use.
3. Write down the identifier of the private video you created, called [video_id].
4. Share it privately only with the targeted victim you'd like to track (e.g. victim@gmail.com) -- see the guide here for more instructions: https://support.google.com/youtube/answer/157177
5. Prepare the state dependent URL as follows: "https://www.youtube.com/embed/[video_id]?rel=0&amp;autoplay=1&mute=1"
6. Change the "State-Dependent-URL" string in source code of each of these attack pages to this URL
7. To experiment with the target state, log out of the attacker's youtube account, and login to the victim's youtube account. Then open the attack page (and click on the webpage for MacInteLSafari), wait for the attack page to load the video, and then record the reported trace. Refresh the attack page and repeat 20 times.
8. To experiment with the non-target state, repeat the previous step, but either in incognito mode or being logged out of youtube.
9. Put the collected traces into the template.json file in USENIX_Artifact_Evaluation directory. First 20 records should be for target state, and second 20 records should be for non-target state.
10. Open https://colab.research.google.com/
2. Upload the USENIX_Artifact_Evaluation/test.ipynb file and open it in Google Colab
3. On the left side of Google Colab interface, click on “Files”, then “Upload to session storage”, and choose the template.json file that contains your collected traces.
4. Set the sweep and interval parameters as suggested in the comments.
5. From the menu on top of Google Colab interface, click on “Runtime”, then “Run all”, and wait until it is finished

After finishing these steps, an average plot is generated. It should be somewhat similar to figure 5 in the paper, demonstrating the differences between the two states. Also, accuracy of the logistic regresssion classifer is reported. For higher accuracy, it is recommended to collect at least 100 traces in each state.
