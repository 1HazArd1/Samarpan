
# Documentation
This documentation contains a set of guidelines to help you during contribution and
a set of rules that is requested to be followed. We are happy and thankful to welcome all the legit 
contributions from anyone willing to improve/add new implementations to this project

# Wireframe of the application
Check the [Wireframe](https://www.figma.com/file/qFdwZSrziy4OLva20BxbBD/Samarpan?node-id=147%3A2) designed for the application in Figma.

# Pre Requisites
- Download and install the latest version of [Git](https://git-scm.com/downloads)
- Create a GitHub account
- Download the latest version of Android Studio. Check for updates if any to avoid any issue 
  during gradle build.
# Setting up the Project
1) Star this repository  

2) Fork this repository
  
3) Clone your forked repository  
	git clone https://github.com/<your_username>/Samarpan.git  
	
4) Use this link to clone this repository inside you local machine in the android studio IDE     
 	a) Open up android studio  
	b) Click get from version control  
	c) Paste the link to clone the repo
	
5) The repository is cloned in your local machine.  
 
![](https://github.com/Suswan114/Samarpan/blob/master/images/clone_screenshot.png)
# Instructions to follow while contributing to SAMARPAN
## Follow proper naming convention

1) Naming convention of the layout files: While creating a layout or a fragment file it should follow the following naming convention:
					  "type of user(user/NGO)" "__" "what is that page for" "_" "if fragment mention fragment"  
					   ```for eg user_login_fragment, ngo_dashboard```.

2) Naming convention of the kotlin files: All the files where the backbone of the application would be coded will have a similar naming 
				         convention as the layout
					 "type of user(user/NGO)" "what is that page for" "if fragment mention fragment"  
					  ```for eg userLoginFragment, ngoDashboard```

3) Defining views:The variable defining views or used for view binding should follow this convention:
                 "type of view" "page it belongs to" "Purpose of the view"  
		  ```for eg: btnUserSignUp```

4) Theme color: Theme color has been defined inside [colors.xml](https://github.com/Diversion2k22/Samarpan/blob/master/app/src/main/res/values/colors.xml) with proper naming    convention.  
  Use the theme color in the entire application according to the wireframe design provided above.

5) Defining Strings: All the elements inside the xml file under android:text="" should not be harcoded text. Define all the text inside strings.xml
		     with proper naming convention:
		     all the name inside a string elements should be like "type of view""what is the purpose of the view""which activty/fragment the view belongs to"  
         for eg: ```<string name="et_email_userLogin">Email</string>```
         
# Guidelines for raising a new issue
## Comment on any existing issue raised by maintainers or raise an issue

 All the issues should follow a set of rules 
  - Each issue should have an appropriate and short title like "Bug in user authentication"
  - Each issue should have appropriate tag associated with it.
  - Be specific with the changes you want to implement through the issue 
  - Whenever a participant raises an issue, by default it would be assigned to that participant due to obvious reasons.
  - Attach a screenshot/clip if applicable
# Contributing to the project
1) Start working on the issue once the maintainers have reviewed the issue 
2) Create a new branch inside android studio before working on any changes
    the branch created should have the following convention. 
    "your name/username""related work"  
     for eg"xyzUserRegistration"  
     
     ![](https://github.com/Suswan114/Samarpan/blob/master/images/branch_screenshot_full.png)
     ![](https://github.com/Suswan114/Samarpan/blob/master/images/branch_sreenshot.png)
 3) Perform the necessary changes or updation
 	a)Make sure anything you do is only related to what issue you are working on 
	b)Ensure that your changes apply to all screensizes
	c)Comments make the code easier to examine so make sure to comment wherever needed
	d)Before committing perform the necessary testing and check the working on the emulator or a device
	e)Make a small clip or take screenshots before and after making changes.

 4) Track your changes  
     ```git add .```
 5) Commit all the changes.  
	a)The commit message should be relevant and short.(usually title of pull request)   
	b)Make sure to condense your changes into a single commit  
	![](https://github.com/Suswan114/Samarpan/blob/master/images/commit_screenshot.PNG)  
 6) Push the committed changes in your feature branch to your remote repo.  


### Guidelines for raising a pull request  

- To create a pull request, click on compare and pull requests. Please ensure you compare your feature branch to the desired branch of the repo you are suppose to make a PR to.   
- Each pull request should have appropriate and short title.  
- The description should be ellaborate explaining the changes with proper screenshots or the clip of the test done on device/emulator.    
- Look out for possible merge conflicts.    
- Please be patient enough. The project maintainers/mentors would review it as per their schedule. Please do not start putting comments like "Please check this" etc.    
- Follow the given [PULL REQUEST TEMPLATE](https://github.com/Diversion2k22/Samarpan/blob/master/.github/PULL_REQUEST_TEMPLATE.md) for creating a pull request.    



   ### Voila ❗ You have made a PR to Samarpan 💥.  
   ## Every Help is appreciated.
