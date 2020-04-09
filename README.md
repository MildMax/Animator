# Animator
Repository for animator project for 5004
README

Animator Assignment 7+8+10 – Joseph Burns, Chris Williams

This README describes the classes, interfaces and enums used in our Animation Model as well as verbose descriptions of why we made the decisions to design our classes in such a way.

-----------------------------------------

CHANGES FROM ASSIGNMENT 7 (to 8):
The biggest change from Assignment 7 to 8 was how we implemented storing shapes and transformations. We realized that the shape and transformation information we were receiving was far more generic than we had anticipated, and our method of creating shapes and transformations was overly complex. As a result, we changed our multiple Shape classes (Circle, Oval Rectangle, Triangle, Square) to be stored in a single ShapeImpl class, and instead use our original ShapeType enum to specify the type of the shape. We also changed our Transformation classes to contain data regarding all types of transformations in a single TransformationImpl class, and return values specific to the transformation being made based on whether or not the start and end values of the width/height/position/color of a shape are the same at the beginning and end of the transformation. We also decided to store RGB color values directly in the Transformation instead of creating our own custom Color class. Our class hierarchy largely stayed the same though, with the AnimationModel storing a list of shapes and each shape storing its own list of transformations. The design stayed the same, the storage of data is what necessitated new design choices. Our choices were largely reductionist for the purpose of simplifying the design.

------------------------------------------

CHANGES FROM ASSIGNMENT 8 (to 10):
The only changes we made from assignment 8 were adding the "playback" identifier to be evaluated from command line arguments and moving common functionality between the existing VisualView and the PlaybackView into a single VisualView abstract class. The functionality for the original VisualView for assignment 8 remains, and the methods moved from the original concrete class to the new abstract class were not modified. The only reason we made this change was to prevent replicating code, and we needed no fundemental changes to incorporate our new view. We also had to make additions to our AnimationRunnerImpl class to accomodate the new view as well as our AnimationView interface. All new methods are suppressed with UnsupportedOperationException in pre-existing views. We do not see these as changes, though, just necessary additions for a view we had no prior knowledge of. We decided to put these methods in the AnimationView interface since they could be useful for a similar view with an interactive GUI in the future.

------------------------------------------ 

--AnimationModel interface--
Our AnimationModel interface declares methods necessary for adding shapes and transformations to the model. These are necessary so that the Animator can dynamically create shapes according to the user’s input. The AnimationModel also defines several getters, including getShapes(), getTransformations(), getTotalTicks(), getShapesAtTick(), getWindowHeight(), and getWindowWidth(), getBoundX(), getBoundY(), getAnimationWidth() and getAnimationHeight() so that the state of the AnimationModel can be checked. Also provides methods that allow the view to appropriately set the size and position of the window the animation will be displayed in.

---------------------------------------------

CHANGES TO ANIMATIONMODELIMPL (from 7 to 8) -- we removed the removeShapes and removeTransformations method based on the input in the text files. We believe they are useful methods, but based on our prior assumptions of input, we realized that our implementation of the removal methods was too specific, and lacked the flexibility to accomodate the potential different types of input. As a result, we decided to remove the method until (theoretically) we saw how a shape or transformation might be removed. There does remain a method to access transformations on a shape as well as the shapes in the model itself, and there exist methods for sorting and traversing either list so, depending on input, the methods for removal would be simple to implement. We also decided to reduce the number of offered constructors, as our view requires some kind of height, width and starting (x,y) values to initialize our SVG and Visual views. We developed methods of arithmetically calculating the size of an animation to guaruntee that the height and width of a window contain the entire animation in the event that the input would not be supplied. As such, developing constructors based on new types of input would be as simple as either employing our methods for calculating height and width or setting a default (0,0) starting value for the window.

--AnimationModelImpl class--
Our AnimationModelImpl class defines the method bodies for the methods declared in the AnimationModel interface. It seeks to provide a snapshot of the information held inside the AnimationModelImpl and to maintain a hierarchical order of Shapes that are responsible for their own transformations. Contains methods for adding Shapes and Transformations to Shapes in the model as well as methods for getting the Shapes stored in the model, the Transformations stored in the Shapes, and information regarding the window of the animation, such as the width and height of the window and its starting position on screen. Also contains methods for producing a String representation of the animation.

--Constructor--
The constructor for the AnimationModel takes an int x specifying the x coordinate of the window that displays the animation, an int y specifying the y coordinate of the window that displays the animation, an int w that specifies the width of the window, and an int h that specifies the height of the window. Initializes a HashMap that is used to store Shapes according to their name. Sets ticks in the animation to 0. Throws an IllegalArgumentException if the specified width or height are less than or equal to zero.

--addShape()--
Our addShape() method takes a Shape object and checks to see if a Shape already exists with the same name. If it does not, it adds the shape to the HashMap. If the shape does exist, it throws an IllegalArgumentException. This helps maintain a unique set of shapes. Note, shapes may be added to the same layer; the builder is responsible for assigning layers to shapes in the order they are added to the model. Shapes that exist on the same layer may clash based on the implementation of the view. Layer does not play into whether or not adding a shape is valid.

--addTransformation()--
Our addTransformation() method takes a String shapeName indicating the name of the shape the transformation should be added to and a Transformation to be added to the shape. If the shape does not exist, throws an exception. If the shape does exist, it checks to see if the shape already has a like transformation that occurs in the same time frame. If a transformation of the same type does exist in the same time frame, it throws an exception. Otherwise, it adds the Transformation to the Shape’s list of transformations. It then determines whether or not the end time of the transformation exceeds the current total number of ticks in the AnimationModelImpl, and if it does, sets the total number of ticks to the end time of the transformation.

--getShapes()--
Our getShapes() method returns a list of Shapes held in the AnimationModelImpl. The values in our Shapes cannot be set outside of the class, so we do not create copies of the shapes as their attributes cannot be changed. We do place the Shapes into a new List so that the HashMap that holds the shapes cannot be modified outside of the class. This provides the user a way of looking at all the shapes inside the AnimationModelImpl without giving access that might affect the container inside the Model.

--getTransformations()--
Our getTransformations() method returns a list of all Transformations on every Shape in the AnimationModelImpl. The attributes inside each transformation cannot be modified by any exist methods so we do not return copies since the attributes cannot be changed outside of the model. We do place the Transformations into a new list, though, so no list of transformations inside AnimationModelImpl can be modified outside of the class. This provides the user a way of looking at all the transformations that will happen in the animation.

--getTotalTicks()--
Our getTotalTicks() method returns the number of ticks in the AnimationModelImpl. This provides the user a way of checking how long, arbitrarily, the animation will be.

--getShapesAtTick()--
Our getShapesAtTick() method will create a new container of shapes with values evaluated at a given tick. It takes a double indicating the tick against which the shapes and their transformations will be evaluated. The tick is then passed to each shape in the shapeList's getShapeAtTick method which returns a copy of itself with appropriately modified values. If the return value is null, the shape does not appear at the given tick. All return values that are not null are placed into a new list and then the list is returned.

--getWindowHeight()--
Our getWindowHeight() method will return an int indicating the height of the window.

--getWindowWidth()--
Our getWindowWidth() method will return an int indicating the width of the window.

--getBoundX()--
Our getBoundX() method returns the x value of the upper left corner of the screen for the visual view.

--getBoundY()--
Our getBoundY() method returns the y value of the upper left corner of the screen for the visual view. 

--getAnimationWidth()--
Our getAnimationWidth() method returns the largest window width that the animation needs in order to be displayed on screen without the edges of the window clipping parts of the animation.

--getAnimationHeight()--
Our getAnimationHeight() method returns the largest window height that the animation needs in order to be displayed on screen without the edges of the window clipping parts of the animation.

--toString()--
Our toString() override method creates a verbose description of the window size and color, the list of shapes that are being created, a list of the appearance and disappearance times of the shapes, and then a list of transformations sorted by chronological order. It is very similar to the original text output specified in Assignment 7, except we list the colors as RGB values instead of "red" or "blue".

---------------------------------------------

--Shape interface--
The Shape interface declares the methods that each Shape should provide such that information regarding the Shape necessary for checking for the shape’s existence and type, adding Transformations to the shape, and getting information regarding data held within the Shape is available to the AnimationModelImpl and the user should they decide to re-use the class outside of the AnimationModelImpl.

---------------------------------------------

CHANGES TO SHAPE CLASSES FROM ASSIGNMENT 7 (to 8):
We realized that our shapes would all be described as having the same type of height and width values as well as position and color values, so to accomodate the input and simplify the design, we merged all of our shapes into a simple ShapeImpl class, and based on the ShapeType enum, arithmetically recalculate values specific to a type of shape from their overall height/width values when we call the data. Still offers the same functionality as before, just changes the way that the data is stored.

---------------------------------------------

--ShapeImpl class--
The ShapeImpl class implements the Shape interface and defines all of the methods described in the Shape interface such that each Shape returns a uniform set of data in all instances to be evaluated by the AnimationModelImpl. Holds attributes for the String name associated with the shape, the layer the shape is on, the height, width, x and y position values, and Color of the Shape represented as 3 RGB values as well as a ShapeType enum so the type of shape can be specified and checked. Holds methods for adding transformations to the shape, getting transformations on the shape, and a method for creating a copy of itself with values corresponding to the current tick of the animation called getShapeAtTick(). Provides get methods for accessing individual fields in the Shape such as getWidth(), getHeight(), getX(), getY(), getR(), getG(), and getB(). We also decided to create two methods getCreateStatement() and getAppearStatement() that return strings providing information about the shape to be used when creating a textual description of the shape. Since these statements appear in different places in our text representation of the animation, we decided to use to specific methods instead of overriding the toString method and putting them in the same place.

--Constructor--
The ShapeImpl constructor takes a String name to be associated with the shape, a ShapeType enum that describes the type of the shape, and a layer that the shape will be displayed on. Sets the initial values of the shape, such as height, width, position (x,y), and color RGB values to 0. Initializes empty list of transformations on construction.

--addTransformation()--
The addTransformation() method takes a Transformation t and checks to see if another Transformation of the same type with overlapping start and end times already exists on the object. If another Transformation of the same type exists in the same time frame, throws an exception. Otherwise, it adds the transformation to the list of transformations on the Shape. Also throws an IllegalArgumentException if the Transformation is null. Also recalculates the start and end times the Shape appears at if the transformation has a start
time before the time the shape appears or has an end time after the shape disappears.

--getName()--
The getName() method returns the String name associated with the Shape. Is used in AnimationModelImpl to check if a shape already exists and to create a key in the HashMap that corresponds to the Shape.

--getType()--
The getType() method returns the type of the Shape. This will be used for type checking when we implement the controller and the view. Since certain shapes will be modified differently (for example, a Circle can’t modify height and width to be different values), getting the type of the shape will be important to ensuring the transformations that occur on it will reflect the constraints of the Shape. We use this to avoid using reflection to check the type of the class, and eliminate the overhead associated with reflection.

--getTransformationDescription()--
The getTransformationDescription() method returns verbose String description of the Transformations on a particular shape. It does not reflect the initial values of the Shape, and instead provides a description of just the Transformations, should the user or the AnimationModelImpl require such a description. Is not put in the toString() method since it creates a description of objects held within the Shape rather than aspects of the Shape itself. 

--getTransformationList()--
The getTransformationList() method creates a new list that consists of the same elements in the list of transformations in the Shape and returns the new list. The elements in the list, the Transformations, all have final attributes so they may not be changed, and are thus added directly rather than creating copies of each transformation. We place the Transformations in a new list, though, so that the list inside of the Shape object cannot be modified outside of the Shape object. Any changes to the list will be reflected in the returned list, not the Transformation list in Shape.

--getWidth()--
Returns the width of the shape.

--getHeight()--
Returns the height of the shape.

--getR()--
Returns the red color value of the shape.

--getG()--
Returns the green color value of the shape.

--getB()--
Returns the blue color value of the shape.

--getX()--
Returns the x coordinate of the shape.

--getY()--
Returns the y coordinate of the shape.

--getStart()--
Returns the time that the shape appears on screen.

--getEnd()--
Returns the time that the shape disappears from the screen.

--getLayer()--
Returns the layer tha the shape will appear on in the animation.

--getShapeAtTick()--
Takes a double tick that represents a certain frame in the animation and creates a new shape of the same type with x, y, width, height, and RBG values modified according to the transformation that is affecting the current shape at the specified tick. Returns the new modified shape. If the shape does not appear or has no transformations, returns a null value.

--getCreateStatement()--
Returns a String indicating the values of the shape when it first appears on screen. Returns an empty string if there are not transformations on the shape.

--getAppearStatement()--
Returns a String indicating when the shape appears and disappears in the view. Returns an empty String if there are not transformations on the shape.

---------------------------------------------

--ShapeType enum--
The ShapeType enum describes 6 types of shapes implemented in our model: CIRCLE, OVAL, ELLIPSE, RECTANGLE, SQUARE, and TRIANGLE. The purpose of this enum is to allow the ShapeImpl class to differentiate between different types of shapes that it can store. This is used for type-checking in the Views so that the view may construct and modify the values in the shape as necessary to produce the correct output. For instance, in the SVG view, the ellipse requires a radius, but we store the overall height of the ellipse. AS such, by checking that the ShapeType is an ELLIPSE, we can appropriately make the necessary arithmetic changes to reflect the height as a vertical radius.

---------------------------------------------

--Transformation interface--
The Transformation interface describes the methods necessary to get information pertaining to the Transformation via get methods. Purposely does not support setting or changing internals of the Transformation, effectively making it Read-Only.

----------------------------------------------

CHANGES TO TRANSFORMATIONS FROM ASSIGNMENT 7 (to 8):
We realized that the way our data was being presented to us was more uniform than we had anticipated, and our prior design with dedicated classes for each transformation wasn't flexible enough to handle the input. So, we consolidated the different classes into a single TransformationImpl class and stored the changes as raw data and calculate the difference between starting and end values when the data is called rather than parsing and analyzing the data when it is stored.

----------------------------------------------

--TransformationImpl class--
The TransformationImpl class implements the Transformation interface and defines the method bodies declared in the interface. Stores the start and end time of the transformation as well as the start and end values for a shapes x position, y position, width, height, and rgb color values. Provides get methods for all informaton in order to evaluate what's being changed over the course of the transformation and to adequately modify the shape that the transformation belongs to and a toString() override method that provides a String representation of the transformation. Is Read-Only once the initial values for the Transformation have been set.

--Constructor--
The TransformationImpl constructor takes a start time for the Transformation, an end time for the Transformation, starting and ending x and y values of the shape's position over the transformation, starting and ending width and height values of the shape's dimensions over the transformation, and starting and ending RGB values of the shape's color over the transformation. Checks to make sure that the start time is not less than 0, the end time is not before the start time, that height and width values are not less than or equal to zero, and that RGB color values are not less than 0 or greater than 255.

--getShapeName()--
Returns the name of the shape that the transformation is associated with.

--getX1()--
Returns the initial x coordinate of the shape over the transformation.

--getX2()--
Returns the ending x coordinate of the shape over the transformation.

--getY1()--
Returns the initial y coordinate of the shape over the transformation.

--getY2()--
Returns the ending y coordinate of the shape over the transformation.

--getW1()--
Returns the initial width of the shape over the transformation.

--getW2()--
Returns the ending width of the shape over the transformation.

--getH1()--
Returns the initial height of the shape over the transformation.

--getH2()--
Returns the ending height of the shape over the transformation.

--getR1()--
Returns the initial red color value of the shape over the transformation.

--getR2()--
Returns the ending red color value of the shape over the transformation.

--getG1()--
Returns the initial green color value of the shape over the transformation.

--getG2()--
Returns the ending green color value of the shape over the transformation.

--getB1()--
Returns the initial blue color value of the shape over the transformation.

--getB2()--
Returns the ending blue color value of the shape over the transformation.

--getStart()--
The getStart() method returns the start tick of the Transformation. Is used to check if other Transformations exist in the same time frame as the current Transformation in AnimationModelImpl and ShapeImpl. Is also used to calculate the modified value of a Shape according to a specified tick by give a frame of reference in relation to the type of change.

--getEnd()--
The getEnd() method returns the end tick of the Transformation. Is used to check if other Transformations exist in the same time frame as the current Transformation in AnimationModelImpl and ShapeImpl. Is also used to calculate the modified value of a Shape according to a specified tick by give a frame of reference in relation to the type of change.

--toString()--
The toString() method returns a String that indicates the time frame in which the Transformation is used as well as specific changes in the transformation. For example, if a shape changes width and height and position over a transformation, states these changes in their own lines and returns them so that specific changes can be evaluated textually.

---------------------------------------------

CHANGES TO ANIMATIONVIEWINTERFACE FROM ASSIGNMENT 8 (to 10):
Added methods to get the animation runner for the new Playback view as well as methods to
set Command Listeners and Mouse Listeners for the new Playback view.

---------------------------------------------

--AnimationView interface--
Our AnimationView interface declares the methods that must in some sense be implemented by every view. These include openView(), which initializes the view, closeView(), which closes the view, run(), which runs the animation, and drawNewFrame(), which takes a list of shapes and draws them to the screen. Also provides a public static method available to all views called DisplayErrorMessage, which uses JOptionPane to display an error message to the screen. Added getRunner() method to get the runner in the visual views, setCommandListener() method to set command listeners for the visual views, and setMouseListener() to set mouse listeners for the visual views and setChangeListener() to set change listeners for visual views.

---------------------------------------------

CHANGES TO ABSTRACTTEXTVIEW CLASS FROM ASSIGNMENT 8:
Implemented get methods required by new visual view and suppressed them with UnsupportedOperationException. We decided to make these views a part of the interface since we could see an instance in the future where they might be needed by another kind of visual view.

---------------------------------------------

--AbstractTextView class--
The AbstractTextView class defines the methods that are relevant to all views that utilize some sort of textual output. Has a constructor that sets the fileName that the textual view will be writing to if one is specified -- fileName may be null in the event that the view writes to System.out and not a file. Sets out to be System.out be default. Defines methods for opening and closing a text file to be written to as well as a method for checking the contents of the file that is being written to. Overrides drawNewFrame() method and throws UnsupportedOperationException. 

--Constructor--
The AbstractTextView constructor takes a single String fileName indicating the file that the text output will be written to. Does not check for a null filename since a null filename indicates that the view should write its output to System.out rather than a specific file.

--openView() method--
If the fileName is not null, opens a file by the fileName stored in the String fileName field and sets the view to write to opened file. If the file cannot be opened, catches an IOException and throws IllegalStateException to indicate that the view cannot write to the file specified.

--closeView() method--
If the output is a file and not System.out, closes the file. Catches an IOException and throws IllegalStateException to indicate that the view could not close the specified file.

--getOutFileContents() method--
If the view is set to write to a file and not System.out, opens the file, reads the contents from the file, places it into a string, closes the file and returns the contents as a String. Exists for testing purposes to ensure view properly writes contents to the file.

--drawNewFrame() method--
Throws an UnsupportedOperationException since text views do not draw frames to the screen.

--getRunner() method--
Throws an UnupportedOperationException since text views do not utilize an AnimationRunner.

--setCommandListener() method--
Throws an UnsupportedOperationException since text views do not utilize command listeners.

--setMouseListener() method--
Throws an UnsupportedOperationException since text views do not utilize mouse listeners.

-----------------------------------------------

--TextViewImpl class--
The TextViewImpl class writes a textual description of the animation to either a specified output .txt file or to System.out. Has two constructors since a fileName does not need to be supplied to create a TextView. Extends the AbstractTextView class.

--TextViewImpl single argument constructor--
The TextViewImpl constructor that takes a single argument takes a String indicating the name of a file that the text output will be written to. If the fileName is null, throws IllegalArgumentException since the file must exist in this instance. If the filename does not end with ".txt", throws IllegalArugmentException since the class must write to a text file if it writes to a file. If the filename is less than 5 characters, including the ".txt", then it does not contain a valid filename, and throws IllegalArgumentException. Passes fileName to the super constructor.

--TextViewImpl no argument constructor--
The TextViewImpl constructor that takes no arugments passes a null fileName value to the super constructor. This will be used to indicate that the textual view should write its output to System.out, the default output for textual views. 

--run() method--
Takes an AnimationModel object that holds the data representing the animation. Calls openView() and closeView() internally to guaruntee that the file is opened and closed each time the run() operation is called. If the output of for the text view is an instance of FileWriter, casts 'out' up to a FileWriter object and writes the textual representation of the animation to the file. Otherwise, appends the textual representation of the animation to System.out. Throws IllegalArgumentException if the AnimationModel is null. Throws IllegalStateException if the FileWriter object of System.Out cannot be written to or if the specified file cannot be written to.

-----------------------------------------------

--SVGViewImpl class--
The SVGViewImpl class writes a text description of an animation to an SVG file formatted in as XML. The created file can be played in an internet browser or opened as a text file. Has methods responsible for opening the SVG file, closing the SVG file, and running the animation, which requires writing a String formatted in XML to the SVG file. Extends the AbstractTextView class which implements the AnimationView interface.

--Constructor--
The SVGViewImpl constructor takes a String representing a path and file name of the SVG file that the XML format will be written to and an int delay that represents the delay in milliseconds between each tick in the transformation. Throws an IllegalArgumentException if the fileName String is null. Also throws an IllegalArgumentException if the specified file doesn't end with the '.svg' extension or if the name of the file is less than 5 characters, including the '.svg' extension (must have a valid filename). Throws IllegalArgumentException if the specified delay is less than 1.

--run() method--
Takes an AnimationModel m that holds data that represents an animation over a specified number of ticks. Formats the list of Shapes in the AnimationModel in XML and then formats the list of instructions on each of the Shapes in the AnimationModel in XML and then writes the final formatted string to the SVG file. Calls openView() before writing to the file and closeView() after the file has been written to to guaruntee that the SVG file is properly opened and closed each time run() is called. Throws IllegalArgumentException if AnimationModel parameter is null. Throws IllegalStateException if the SVG file cannot be written to.

-----------------------------------------------

CHANGES TO VISUALVIEWIMPL CLASS FROM ASSIGNMENT 8 (to 10):
We moved much of the functionality shared between the new PlaybackView and the existing VisualView to a single abstract class to avoid duplication of code. Suppressed new methods required for PlaybackViewImpl, such as getRunner(), setCommandListener(), and setMouseListener().

-----------------------------------------------

--VisualViewImpl class--
The VisualViewImpl class is responsible for creating a visual view that can display an animation on screen. Extends the AbstractVisualView class which extends the JFrame class and implements the AnimationView interface. Initializes a frame based on values supplied from the AnimationModel and contains methods for running the animation.

--VisualViewImpl constructor--
The VisualViewImpl constructor takes an int x and y values inicating the top left corner of the screen, an int windowWidth and windowHeight indicating the width and height of the window, an int maxWidth and maxHeight indicating the max width and height of the animation, and an int ticksPerSecond that indicates how many ticks pass in a second. Passes these values down to the AbstractVisualView super constructor. If either widths or heights are less than or equal to 0, throws an IllegalArgumentException. IF x or y is less than 0, throws IllegalArgumentException so window cannot be instantiated off the screen. If the ticksPerSecond is less than 1, throws IllegalArgumentException. Creates a ShapePanel object that the shapes will be drawn to, places the ShapePanel in a JScrollPane class, and places the JScrollPane into the current JFrame class. Sets the width and height of the window to windowWidth and windowHeight, sets width and height of the ShapePanel to the maxWidth and maxHeight class so scroll bars can be used to view entire animation. Sets default close operation on JFrame to exit on close.

--run() method--
The run method takes an AnimationModel object to be passed to an AnimationRunner class. Creates a new instance of the AnimationRunner class and passes the AnimationModel parameter as the model, itself as the view, and the ticks per second stored in the class. Calls runAnim() on the AnimationRunner instance to display the animation and startAnim() to play the animation. Throws an IllegalArgumentException if the AnimationModel parameter is null since the AnimationRunner cannot produce a visual without it. Closes the window and exits the program upon completing the animation.

--getRunner() method--
Throws an UnsupportedOperationException since there is no work to be done on the AnimationRunner in the VisualViewImpl class.

--setCommandListener() method--
Throws an UnsupportedOperationException since the VisualViewImpl class doesn't utilize command listeners.

--setChangeListener() method--
Throws an UnsupportedOperationException since the VisualViewImpl class doesn't utilize
change listeners.

--setMouseListener() method--
Throws an UnspportedOperationException since the VisualViewImpl class doesn't utilize mouse listeners.


-----------------------------------------------

--PlaybackViewImpl class--
The PlaybackViewImpl class is responsible for creating a visual view that not only displays an animation on screen, but provides an interactive GUI that lets the user manipulate the animation. Provides functionality for playing/pausing the animation, restarting the animation, and setting the speed of the animation. Extends the AbstractVisualView class which extends the JFrame class and implements the AnimationView interface.

--Constructor--
The AbstractVisualView constructor takes an int x and y values inicating the top left corner of the screen, an int windowWidth and windowHeight indicating the width and height of the window, an int maxWidth and maxHeight indicating the max width and height of the animation, and an int ticksPerSecond that indicates how many ticks pass in a second. Passes these values to be checked by the super constructor in AbstractVisualView. If either widths or heights are less than or equal to 0, throws an IllegalArgumentException. IF x or y is less than 0, throws IllegalArgumentException so window cannot be instantiated off the screen. If the ticksPerSecond is less than 1, throws IllegalArgumentException. Creates a ShapePanel object that the shapes will be drawn to, places the ShapePanel in a JScrollPane class, and places the JScrollPane into a JSplitPane class as the top component. Sets the width and height of the window to windowWidth and windowHeight, sets width and height of the ShapePanel to the maxWidth and maxHeight class so scroll bars can be used to view entire animation. If the specified height of the window is greater than that of the screen, resizes the display to such that the window is displayed entirely on current screen. Creates the top and bottom panels of the window. Sets the buttons, checkboxes, and slider in the bottom part of the window. Sets the program to close on exit. Formats the elements on the screen. If ticks per second is less than 10, sets slider to 10. If ticks per second is greater than 200, sets slider to 200.

--getRunner() method--
Retrieves the AnimationRunnerImpl from the current class.

--setPlayText() method--
Changes the text on the Play/Pause button to a parameter String text. If the animation is playing, sets text to be "Pause". If the animation is not playing, sets text to be "Play" if the animation is at the beginning or end; otherwise sets text to be "pause". Only exists in the current class as no other views require stopping or starting of animation.

--setAnimSpeed() method--
Sets the speed in ticks per second of the animation to what is displayed on the Slider component in the view. Only exists in current class as no other views utilize a slider.

--setCommandListener() method--
Sets an ActionListener to be attached to the play/pause button, the restart button, and the loop check box. Throws IllegalArgumentException if the ActionListener is null.

--setChangeListener() method--
Sets a ChangeListener to be attached to the ticks per second slider. Throws an IllegalArgumentException if the ChangeListener is null.

--setMouseListener() method--
Sets a MouseListener to be attached to the shapePanel that displays the animation. Throws IllegalArgumentException if the MouseListener is null.

--getPlayButton() method--
Gets the play button in the class. EXISTS FOR TESTING PURPOSES ONLY.

--getRestartButton() method--
Gets the restart button in the class. EXISTS FOR TESTING PURPOSES ONLY.

--getLoopBox() method--
Gets the loop check box in the class. EXISTS FOR TESTING PURPOSES ONLY.


-----------------------------------------------

--AbstractVisualView class--
The AbstractVisualView class contains methods and constructors common to both the VisualViewImpl and the PlaybackViewImpl classes. Suppresses the getFileOutContents() method, and provides common functionality for openView(), closeView(), drawNewFrame(), and run(). Also provides a super constructor that checks values from any given constructed visual view. The fields in the Abstract class, shapePanel, scrollPabne, runner, and ticksPerSecond are made protected since access to these fields is required to change or get information internally on these fields. Do not provide getters or setters for these fields since direct access is frequently required by child classes.

--Constructor--
The AbstractVisualView constructor takes an int x and y values inicating the top left corner of the screen, an int windowWidth and windowHeight indicating the width and height of the window, an int maxWidth and maxHeight indicating the max width and height of the animation, and an int ticksPerSecond that indicates how many ticks pass in a second. If either widths or heights are less than or equal to 0, throws an IllegalArgumentException. I x or y is less than 0, throws IllegalArgumentException so window cannot be instantiated off the screen. If the ticksPerSecond is less than 1, throws IllegalArgumentException. Creates a ShapePanel object that the shapes will be drawn to, places the ShapePanel in a JScrollPane class, and places the JScrollPane. Sets the width and height of the window to windowWidth and windowHeight, sets width and height of the ShapePanel to the maxWidth and maxHeight class so scroll bars can be used to view entire animation. 

--openView() method--
Makes the Display window visible.

--closeView() method--
Makes the Display window invisible and removes the window from the current class. Also exits the program when called.

--run() method--
The run method takes an AnimationModel object to be passed to an AnimationRunner class. Creates a new instance of the AnimationRunner class and passes the AnimationModel parameter as the model, itself as the view, and the ticks per second stored in the class. Calls runAnim() on the AnimationRunner instance to display. Throws an IllegalArgumentException if the AnimationModel parameter is null since the AnimationRunner cannot produce a visual without it.

--getOutFileContents() method--
Throws an UnspportedOperationException since the visual view does not write textual output to a file.

-----------------------------------------------

--ShapePanel class--
The ShapePanel class handles drawing shapes to a JPanel object that will be displayed in the view. Extends the JPanel Class. Has an addFrame() method that takes a list of shapes that will be drawn in the paintComponent() method.

--addFrame() method--
The addFrame method takes a list of Shapes with values at a particular frame in the animation and stores it to be accessed by the paintComponent method. Throws an IllegalArgumentException if the shapeList is null. 

--paintComponent() method--
Overrides the paintComponent method from the JPanel class and draws shapes stored in shapeList to the panel. 

-----------------------------------------------

--AnimationRunner interface--
The AnimationRunner interface defines methods necessary for modifying the animation according to user input via mouse clicks, button clicks, and general user input. Contains methods startAnim(), restartAnim(), toggleLoop(), togglePlay(), isRunning(), and setTicksPerSecond().

-----------------------------------------------

CHANGES TO ANIMATIONRUNNERIMPL FROM ASSIGNMENT 8 (to 10):
We implemented an interface since our AnimationRunner required a slew of new public methods needed in order to tie buttons to various listeners and have the animation reflect the input from those listeners/buttons/sliders. The AnimationRunner now contains a startAnim() method, a restartAnim() method, a toggleLoop() method, a togglePlay() method, an isRunning() method, and a setTicksPerSecond() method. We made this design choice because we wanted to ensure our runner contained all the methods needed to adequately modify the state of the animation. 

-----------------------------------------------

--AnimationRunnerImpl class--
The AnimationRunner class contains operations for determining the frames per tick, starting the animation, checking the state of the animation, and then closing the animation. Extends the ActionListener class and implements the JSwing timer class. Passes itself to JSwing timer. Takes a value in ticks per second and converts it to frames per tick and runs at approximately 60 frames per second while still reflecting the specified speed of the animation in ticks. Handles running the animation. Is only public for testing purposes, otherwise will be made package protected since it should be internal to the visual view. Also contains public methods getTicksPerFrame(), getTimer(), getIsLooping(), getFrames(), getView(), and getModel() for testing purposes ONLY. Are not in interface as they are not required by a runner class and exist SOLEY FOR TESTING. Implements the ActionListener and AnimationRunner interfaces. Uses ActionListener to listen to internal Timer class.

--Constructor--
The AnimationRunner constructor takes an AnimationModel, an AnimationView that will display the shapes at each frame to the screen, and an int value ticksPerSecond. Converts the ticks per second into ticksPerFrame. Sets the delay for the timer stored in the class to milliseconds per frame, which is found by dividing 1000/60 to ensure the animation always plays at approximately 60 frames per second.

--startAnim() method--
The runAnim() method opens starts the timer that will call the actionPerformed method in the current listener.

--restartAnim() method--
Sets the state of the animation to the first tick in the animation. Does not change the play state of the animation. If called while animation is playing, animation will continuing playing from first tick. If called while animation is not playing, animation will be reset to first tick and stay there.

--toggleLoop() method--
Toggles whether or not the animation is set to loop. If the animation is set to loop, will set animation to not loop. If the animation is not set to loop, will set animation to loop.

--togglePlay() method--
Toggles whether or not the animation is playing. If the animation is playing, pauses the animation. If the animation is paused, plays the animation. If the animation is over, resets the animation to the beginning and plays from the start.

--setTicksPerSecond() method--
The setTicksPerSecond() method takes an integer ticksPerSecond and calculates the number of ticks per frame the animation should run at, since the animation always runs at approx 60fps. Recalculates current frame based on ticks per second to ensure animation is not reset to earlier or later frame. Throws IllegalArgumentException if ticksPerSecond argument is less than or equal to 0.

--isRunning() method--
Returns whether or not the timer within the animation is running. If the timer is running, returns true. If the timer is not running, returns false.

--getTicksPerFrame() method--
Exists to test ticks per frame. FOR TESTING ONLY.

--getTimer() method--
Exists to test state of timer. FOR TESTING ONLY.

--getIsLooping() method--
Exists to test whether or not the animation is looping. FOR TESTING ONLY.

--getFrames() method--
Exists to test the frames value in the runner. FOR TESTING ONLY.

--getFPS() method--
Exists to test the frames per second value in the runner. FOR TESTING ONLY.

--getView() method--
Exists to get the view from the runner. FOR TESTING ONLY.

--getModel() method--
Exists to get the model from the runner. FOR TESTING ONLY.

--actionPerformed() method--
The actionPerformed() method keeps track of the number of frames that have passed. Determines if the number of frames surpasses the number of ticks in the animation, and once the animation has run, stops the timer and closes the view. Otherwise, increments the frames and draws a new frame according to the values of the shapes at the given frame in the AnimationModel to the view.

------------------------------------------------

--AnimationController interface--
The AnimationController interface describes the method needed to run the the animation from the controller. Contains go() method.

--go() method--
Sets up the view and the model to run the animation and then runs the animation.

------------------------------------------------

--AnimationControllerImpl class--
The AnimationControllerImpl class defines the methods needed to control the flow of the animation. Holds an AnimationModel that stores data describing the animation and an AnimationView that will display the animation. Implements the AnimationController interface.

--Constructor--
The AnimationController constructor takes an AnimationModel and an AnimationView and stores them in the class to be used to run the animation. Throws an IllegalArgumentException if the AnimationModel or the AnimationView is null.

--go() method--
The go() method is responsible for setting up the view to display the animation correctly. Runs the animation by passing the AnimationModel to the run() method in the view. If the view is an instance of PlaybackViewImpl, sets the ButtonListener, SliderChange Listener, and MouseListener values in the view.

--getAnimationModel() method--
Gets the model from the controller. EXISTS ONLY FOR TESTING PURPOSES.

--getAnimationView() method--
Gets the animation view from the controller. EXISTS ONLY FOR TESTING PURPOSES.

------------------------------------------------

--ButtonListener class--
The ButtonListener class holds an AnimationRunner that runs an animation with a GUI that utilizes an ActionEvent/ActionListener combination to execute the relevant command. Implements the ActionListener interface. Normally, since only the AnimationController
instantiates the ButtonListener, we would make this class package protected so only the Controller has access to it, but for testing purposes, we made the class public.

--Constructor--
The ButtonListener constructor takes an AnimationRunner that uses an ActionListener to handle button presses in the view to make changes to the animation. Throws an IllegalArgumentException if the AnimationRunner is null.

--actionPerformed() method--
The actionPerformed() method is responsible for taking an ActionEvent e from a button in the view GUI, takes a tag from the ActionEvent, and uses it to execute the appropriate method in the AnimationRunner. This design allows us to handel all button presses within the same method. Any new buttons can be set to the same listener by giving the button in the view an actionCommand tag and setting up the appropriate case in this method.

------------------------------------------------

--SliderChangeListener class--
The SliderChangeListener class holds an AnimationView that contains a slider in the GUI that utilizes a ChangeEvent/ChangeListener combination to reflect changes made by the user in the AnimationView. Implements the ChangeListener interface.

--Constructor--
The SliderChangeListener constructor takes an AnimationView that contains a slider which may be changed during the running of the program. Throws an IllegalArgumentException if the AnimationView is null.

--stateChanged() method--
The stateChanged() method takes a ChangeEvent e attached to a slider component responsible for changing the speed of the animation in the AnimationView class stored in the current object and calls the setAnimSpeed() method in the PlaybackViewImpl to get the new value from the slider and pass it to the AnimationRunner to change the speed. Only contains functionality for the PlaybackViewImpl class since it is the only class whose speed can be changed and responds to a slider -- the other views do not contain a setAnimSpeed() method since their speeds are set when the views are constructed.

------------------------------------------------

--TogglePlayMouseListener class--
The TogglePlayMouseListener class holds an AnimationRunner that pauses/starts the animation in the AnimationView when the window holding the animation is clicked. Extends the MouseAdapter class. 

--Constructor--
The TogglePlayMouseListener constructor takes an AnimationRunner that starts/pauses the animation. Throws an IllegalArgumentException if the AnimationRunner is null.

--mouseClicked() method--
Takes a MouseEvent and toggles the current state of the animation when the screen holding the animation is right clicked by the mouse. If the animation is playing, sets the animation to a paused state. If the animation is paused, sets the animation to a playing state. If the animation has ended and looping is not set, cannot be used to restart the animation. If the left mouse button is clicked on the screen, restarts the animation.

------------------------------------------------

--EasyAnimator class--
The EasyAnimator class contains a static Main method that runs the application. Parses command line arguments and initializes the AnimationModel and the AnimationView according to the command line arguments and creates the AnimationController from the constructed model and view. If invalid command line arguments are supplied or there are missing command line arguments, uses the static displayAnimationView from AnimationView interface to display an error message to the screen. Is responsible for setting up animation according to command line arguments and connecting the view and model via the controller.

--main() method--
A static method that creates an EasyAnimator object that lets an AnimationModel and AnimationView communicate with one another to create an animation. Parses command line arguments and uses them to populate the AnimationModel with data representing an aanimation and creating a specific Textual, SVG, or Visual view. Then creates AnimationController with the constructed view and model and sets the controller to run the animation.