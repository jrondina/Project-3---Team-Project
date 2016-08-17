# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Project #3: TouchLab Card View App

#### Overview

Project 3 is your opportunity to really let your creativity shine! You will have the basic app requirements, provided by TouchLab, but it is up to you to design everything using the research you did with your group. In this project you’ll be building a “Google Now”-style feed. Each item in the feed will be a card that the user can scroll through and interact with.

**You will be working in groups for this project**. The project will be spread across **three weeks**, with separate deliverables due at the end of each week. Although the deliverables will be separate, we will be incorporating them all into a single app, expanding upon the previous week's work. The first week will focus on the research; the second week on your group's project proposal, synthesis from your research, and a basic UI; and the third week on the technical implementation - integrating the various APIs, design and functionality together.


---

#### Requirements / Constraints

Your work must:

- Have at least one Activity--more if it makes sense. The activity should include a list of interactive cards implemented with Android’s RecyclerView and CardView APIs.	
- Include at least three types of cards:	
	- One card should present data that is stored and updated on the user’s phone using a Content Provider or Shared Preferences (e.g. To-do card, Reminder card, Alarm card).
	- Two cards should present data provided through a web API (e.g. Weather card, Twitter card, Maps card, Photo of the Day). Check out [this directory of APIs](http://www.programmableweb.com/apis/directory) if you need some API ideas.
- Use the [Retrofit library](https://square.github.io/retrofit/) to handle HTTP requests and parse JSON data	
- Include **at least 2 prototypes**
- Include user stories based on your research and feature prioritization in a [Trello board](http://buildbettersoftware.com/trello-for-software-development) for the **complete flow of your app**
- Integrate with the Twitter and/or Facebook APIs to allow sharing of content via social media
- Look great in both landscape and portrait modes and reflect Material Design principles
- Include at least one **Notification** feature (e.g. reminder, alarm)
- Include **automated testing** (and manual testing if needed) to cover your app.
- Use **JobScheduler** to manage API calls
- Not crash or hang and should handle for when networking/internet is slow or unavailable
- Have code that is semantically clean and well-organized


**Bonus:**

- Integrate additional APIs (news, other content, social, etc.)
- Integrate with a mapping and/or navigation API
- Integrate with a messaging/communication API such as Slack
- Allow the user to save, bookmark, or ‘like’ content within the app

---

#### Code of Conduct

As always, your app must adhere to General Assembly's [student code of conduct guidelines](https://github.com/ga-adi-nyc/Course-Materials/blob/master/markdown/code-of-conduct.md).

If you have questions about whether or not your work adheres to these guidelines, please speak with a member of your instructional team.

---

#### Necessary Deliverables

**Week 7:**

By the end of week 7:

- A completed research plan according to the template provided in class
- Competitive research in a Google Sheet

**Week 8:**

By the end of week 8:

- Written user personas--ideally from both new and longtime Yelp user perspectives
- A list of prioritized features
- A project plan presentation
- Completed user stories
- A link to your team's Trello Board set up according to [Trello's suggestions](http://buildbettersoftware.com/with-trello/)
- A **completed, basic version of the user interface for your app**, with placeholder data and resources to fill out your screens

**Week 9:**

By the end of week 9:

- Working cloud synchronization using Sync Adapters and API integration
- A final, working version of your app
- A **git repository hosted on GitHub**, with frequent commits dating back to the **very beginning** of the project. Commit early, commit often
- **A ``readme.md`` file** describing what the app does, and any bugs that may exist
- One screenshot in the ``readme.md``
- Automated tests for your code

---

#### Suggested Ways to Get Started

- Complete as much of the layout as possible before working on your logic.
- Write pseudocode before you write actual code. Thinking through the logic helps.
- Test functionality as soon as you complete it.

---

### Useful Resources

- [Directory of APIs](http://www.programmableweb.com/apis/directory)
- [Android API Reference](http://developer.android.com/reference/packages.html)
- [Android API Guides](http://developer.android.com/guide/index.html)
- [Creating a Sync Adapter](http://developer.android.com/training/sync-adapters/creating-sync-adapter.html)
- [Twitter for Developers](https://dev.twitter.com/)
- [Facebook for Developers](https://developers.facebook.com/)

---

#### Project Feedback + Evaluation

Your instructors will score each of your technical requirements using the scale below:

    Score | Expectations
    ----- | ------------
    **0** | _Incomplete._
    **1** | _Does not meet expectations._
    **2** | _Meets expectations, good job!_

 This will serve as a helpful overall gauge of whether you met the project goals, but __the more important scores are the individual ones__ above, which can help you identify where to focus your efforts for the next project!
