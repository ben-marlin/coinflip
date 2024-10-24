# Flipping a Coin

## Sample code

When you run the sample code, it will prompt you to call a coin as heads or tails. Then it flips the coin and tells you whether you won (matched) or lost. 

Looking at the code, you'll see a number of things that are less than desirable about this version.

- does not repeat
- data validation is clunky
- using String method equals
- not visual or dynamic

## JFrame version

In the days of DOS & mainframes, text entry was what we had. But in modern computing, you expect to have a window and a visual representation. It should be intuitive and easily repeatable. So let's build one.

## Create the class

You should recall from the JFrame drawing project how to get started. If not, open that project repo in a browser window so you can look back at it.

Start by creating a class: File > New File > Java File > Class > CoinFlip. As some of us have learned the hard way, don't type the .java - let the computer supply that part! 

The result should be a file that supplies you with `public class CoinFlip` at the top and nothing else. Add the `package edu.guilford;` line at the top, followed by the comments for your name. At later points, VSCode should add import statements for your use of Random, JFrame, etc., but if it doesn't you may need to return here.

## The imports

Here's the collection of imports that I needed.
```
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
```

## Base class modifications

Modify your header for the class as in the previous: `public class CoinFlip extends JFrame`. This should add the import statement, automatically, but if it doesn't, add `import javax.swing.JPanel;`

Now add a constructor. You can copy the one from your previous project and modify it or copy this.
```
public CoinFlip() {

    // instantiate the randomizer for later
    random = new Random();

    // set up the frame
    setTitle("Coin Flipper");
    setSize(350, 350); 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    setLocationRelativeTo(null);
}
```

## Add a main method

As before, we'll need a `main` just to instantiate the JFrame. There's nothing fancy about this, so you can copy the one from the drawing project verbatim or copy it from here. It needs to be inside the CoinFlip class, but after the CoinFlip constructor.
```
public static void main(String[] args) {
    JFrame frame = new CoinFlip();
    frame.setVisible(true);
}
```
This is the bare minimum for testing. Run your code and you should get a JFrame window. Remember that this is our strategy: get one piece to work before adding another... build it one piece at a time!

## Add the panel

In the previous project, we wanted to draw shapes onto our JPanel, so we had to be a little fancy. This time, we'll be able to use a standard JPanel, but we're going to take advantage of some of the features of the component.

In the CoinFlip constructor, add the following before the setup of the frame code.
```
// Create panel
JPanel panel = new JPanel();
panel.setBackground(Color.WHITE);
panel.setLayout(new BorderLayout());

// puts the panel into the frame
add(panel);
```
Test this to make sure it works. It shouldn't do anything new but change the background color. 

A JPanel is a type of object we call a "container". We put other objects in it, and it handles how to lay them out. If you do nothing with the layout, then the `setLayout` line is unnecessary. While this will work, it means your components will move around unpredictably when the window is resized. Best to learn how to use these early and make neat apps.

Here we have added a `BorderLayout`. This has five subdivisions or "panes". These are `BorderLayout.CENTER`, `BorderLayout.NORTH`, `BorderLayout.SOUTH`, `BorderLayout.EAST`, and `BorderLayout.WEST`. Secretly, those names hide integers, but nobody memorizes those because the locations are intuitive (if you're used to having north be up on your maps).

There are plenty of other Layouts, including BoxLayout, CardLayout, FlowLayout, GridBagLayout, GridLayout, and SpringLayout. We will discuss these as needed, but if you want to know more, Google is a good resource.

## Making resources global

Just under the signature for the class - before the constructor - add the following.
```
private JLabel coinLabel;
private ImageIcon headIcon;
private ImageIcon tailIcon;
private Random random;
private JButton flipButton;
```
Remember from the dice & card classes that we had fields that were accessible to all the methods of the class. These operate like that.

## Images

Notice that I dropped two png files into your repo. They are titled head.png and tail.png. You won't be surprised by what they are, but click on them to see that VSCode can display them for you.

It is entirely possible you won't like those images. If that is the case, you can visit a website like images.google.com or shutterstock.com or clipart.com and find replacements. If your file is named something different, you will need to adjust the code that mentions these.

Notice that I put these files in a folder called `resources`. This is considered good practice in Java programming with projects. You may see example code that places image files in the same folder as the program you're writing, but I've had some difficulty making relative path names work.

## Adding a picture of the coin

We're going to add a picture of the coin. This is a little complicated because of the variety of systems the class deals with. Add this code to the beginning of the CoinFlip constructor.
```
// Initialize components
headIcon = new ImageIcon("src/main/resources/head.png");
tailIcon = new ImageIcon("src/main/resources/tail.png");
coinLabel = new JLabel(headIcon);
```
Look at the name of the file and compare to the folder structure in your Explorer. All your programming stuff is in the `src` folder and in particular your program is in `src/main/java/edu/guilford`. But back in `main` there was a fork that went instead to `src/main/resources`. If you put your extra files in `resources` all the time, it will be easier to keep track of.

Now, after the line where you added the button to the panel, add one that says `panel.add(coinLabel, BorderLayout.CENTER);`. This means you've created an ImageIcon for each picture of the coin, then created the coinLabel with one of the pictures, and finally added it to the JPanel.

If you test your program at this point, it will give you errors because we forgot to declare headIcon, tailIcon, and coinLabel! This was strategic, as we're pulling a trick.

## Adding the JButton

We're going to use a button to issue the command to flip the coin. To add a simple button, use the following code. Add it to the CoinFlip constructor before the code that creates the JPanel.
```
// Create button and add action listener
flipButton = new JButton("Flip Coin");
```
And now, on the line after the Layout was set, add a line that says `panel.add(flipButton, BorderLayout.SOUTH);`. Now, when you test the program, it should show a button at the bottom, and it will be the width of the window.

With this addition, you should be able to test your code, and it should display the head of a coin. There's a button, but it doesn't do anything. Let's fix that.

## Making the button functional

First, we're going to add a "listener" to the button. To do this, right after the line that instantiates the button, add this.
```
flipButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        flipCoin();
    }
});
```
If you test your code right now, there will be an error, as we have not written the flipCoin method. Let's make a minimal working method to fix that. Add this code somewhere between the end of the CoinFlip constructor and the main method.
```
private void flipCoin() {

}
```
Once you have added this, the error should go away. But the program still doesn't do anything. To explain, we've added an ActionListener - a piece that "listens" to a button to see when you click on it. And the actionPerformed is specified as running the flipCoin method. We could have just done the actions that follow inside actionPerformed because it's not complicated. But it's best to get in a habit of dealing with more complicated things now.

Inside flipCoin, add the following code.
```
if (random.nextBoolean()) {
    coinLabel.setIcon(headIcon);
} else {
    coinLabel.setIcon(tailIcon);
}
```
The method nextBoolean() produce either a `true` or a `false`. This code changes the icon for coinLabel to headIcon if it turns up true and tailIcon if it turns up false.

## Imports 

If you've run across anything that can't resolve as you went along, you may need to add some of the following import statements. Remember that these go after your name, but before the class header.
```
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
```
My VSCode installation does a pretty good job of adding these as necessary, but a few people have had instances where it didn't add the needed import - or worse, it picked something different with a similar name!

## Testing

Run the program and click the button. Since it's 50/50 whether you get true or false in flipCoin, it may not flip every time, but if you click it enough times, it should change.

## Wrapping up

At the end, you should have a working app that flips a coin for you. If you're interested, you can go find different images of a coin. If you do, you may want to change the background color for the panel to match - or find images with transparent backgrounds. Either way.

Once this works, save it - stage it - commit message - commit button - sync button. The usual! But then go to Canvas and post a screenshot of your app working along with a link to your repo on GitHub.
