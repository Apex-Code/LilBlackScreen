# LilBlackScreen
Java example of proper memory management.


Lil Black Screen would have been an amazing application **in 1986**.  

Some type of CRUD style address book seems to be the bread and butter of college curriculum.  A project like that does an adequate job of 
introducing OOP concepts.  Unfortunately, just when students discover the key concepts of OOP, they also discover that they just wrote all 
those lines...  Well... *wrong*.  

When this project is complete, it will be fast, efficent and well coded.  The only point of this project is so those newer to Java can learn 
a bit about memory management.  Which is a concept that isn't (in my opinion) taught very well.  

I'll be updating this Readme throughout this little adventure, so without further rambling...

**12/11/17**  
Looking much better now, imho.  Most of the changes involved using while loops > switch statements vs straight switch.  This reduced the thread stack considerably.  Other improvements were converting the setters to booleans.  Rather than approaching the user input from the angle of:
1.  User enters choice
2.  User presses enter
3.  Screen clears
4.  Next User input
5.  Repeat

It was changed to hold input areas on the screen.  Which was a pretty big improvement, both visually and functionally.

Next upgrade will be within the week and will include reading and writing from files.  

# For those who find yourself on a MS box, you're going to need something that simulates Linux/Bash in the shell.  
This is a vanilla Java CLI application.  It uses something called ansiText(to display colors and patterns in the console).  Windows doesn't like
that, so it blows up on a Windows machine.  If you run the Jar under a Git console (https://git-scm.com/download/win) it will be "close."

#Those who would serve...
If you find something that might be a better approach to fixing the app, submit a pull request, because I <3 ideas.

Thanks for stopping by...

  ~Havick
