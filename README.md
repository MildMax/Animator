# Animator
Repository for animator project for 5004
README

Animation Model Assignment 7+8 – Joseph Burns, Chris Williams

-----------------------------------------

CHANGES FROM ASSIGNMENT7:
The biggest change from Assignment 7 to 8 was how we implemented storing shapes and transformations. We realized that the shape and transformation information we were receiving was far more generic than we had anticipated, and our method of creating shapes and transformations was overly complex. As a result, we changed our multiple Shape classes (Circle, Oval Rectangle, Triangle, Square) to be stored in a single ShapeImpl class, and instead use our original ShapeType enum to specify the type of the shape. We also changed our Transformation classes to contain data regarding all types of transformations in a single TransformationImpl class, and return values specific to the transformation being made based on whether or not the start and end values of the width/height/position/color of a shape are the same at the beginning and end of the transformation. We also decided to store RGB color values directly in the Transformation instead of creating our own custom Color class. Our class hierarchy largely stayed the same though, with the AnimationModel storing a list of shapes and each shape storing its own list of transformations. The design stayed the same, the storage of data is what necessitated new design choices. Our choices were largely reductionist for the purpose of simplifying the design.

------------------------------------------

This README describes the classes, interfaces and enums used in our Animation Model as well as verbose descriptions of why we made the decisions to design our classes in such a way. 

--AnimationModel interface--
Our AnimationModel interface declares methods necessary for adding shapes and transformations to the model. These are necessary so that the Animator can dynamically create shapes according to the user’s input. The AnimationModel also defines several getters, including getShapes(), getTransformations(), getTotalTicks(), getShapesAtTick(), getWindowHeight(), getWindowWidth(), and getBackgroundColor() so that the state of the AnimationModel can be checked.

---------------------------------------------

CHANGES TO ANIMATIONMODELIMPL -- we removed the removeShapes and removeTransformations method based on the input in the text files. We believe they are useful methods, but based on our prior assumptions of input, we realized that our implementation of the removal methods was too specific, and lacked the flexibility to accomodate the potential different types of input. As a result, we decided to remove the method until (theoretically) we saw how a method might be removed. There does remain a method to access transformations on a shape as well as the shapes in the model itself, and there exist methods for sorting and traversing either list so, depending on input, the methods for removal would be simple to implement. We also decided to reduce the number of offered constructors, as our view requires some kind of height, width and starting (x,y) values to initialize our SVG and Visual views. We developed methods of arithmetically calculating the size of an animation to guaruntee that the height and width of a window contain the entire animation in the event that the input would not be supplied. As such, developing constructors based on new types of input would be as simple as either employing our methods for calculating height and width or setting a default (0,0) starting value for the window.

--AnimationModelImpl class--
Our AnimationModelImpl class defines the method bodies for the methods declared in the AnimationModel interface. It seeks to provide a snapshot of the information held inside the AnimationModelImpl and to maintain a hierarchical order of Shapes that are responsible for their own transformations.

--Constructors--
In our AnimationModelImpl (the implementation of the model defined in the AnimationModel interface) we provide 4 constructors:
The first constructor is the default constructor: it sets the height and width of the window to 500 and sets the background color to White(0.0,0.0,0.0 in RGB values). We also provide three other constructors that allow the user to define the background color, the window width and height, and both the background color and the window width and height. It does not allow for negative width or height for window size. The constructors also initialize a HashMap to store shapes according to their shapeName string value so that any shape can be accessed in constant time. Also initializes total ticks for the animation to be 0.

--addShape()--
Our addShape() method takes a Shape object and checks to see if a Shape already exists with the same name. If it does not, it adds the shape to the HashMap. If the shape does exist, it throws an exception. This helps maintain a unique set of shapes. Note, shapes may be added to the same layer; it is up to the user or the controller to determine how layers will be assigned. Shapes that exist on the same layer may clash based on the implementation of the view. Layer does not play into whether or not adding a shape is valid.

--addTransformation()--
Our addTransformation() method takes a String shapeName indicating the name of the shape the transformation should be added to and a Transformation to be added to the shape. If the shape does not exist, throws an exception. If the shape does exist, it checks to see if the shape already has a like transformation that occurs in the same time frame. If a transformation of the same type does exist in the same time frame, it throws an exception. Otherwise, it adds the Transformation to the Shape’s list of transformations. It then determines whether or not the end time of the transformation exceeds the current total number of ticks in the AnimationModelImpl, and if it does, sets the total number of ticks to the end time of the transformation.

--getShapes()--
Our getShapes() method returns a list of Shapes held in the AnimationModelImpl. The values in our Shapes are all final, so we do not create copies of the shapes as their attributes cannot be changed. We do place the Shapes into a new List so that the HashMap that holds the shapes cannot be modified outside of the class. This provides the user a way of looking at all the shapes inside the AnimationModelImpl.

--getTransformations()--
Our getTransformations() method returns a list of all Transformations on every Shape in the AnimationModelImpl. The attributes inside each transformation are final so we do not return copies since the attributes cannot be changed outside of the model. We do place the Transformations into a new list, though, so no list of transformations inside AnimationModelImpl can be modified outside of the class. This provides the user a way of looking at all the transformations that will happen in the animation.

--getTotalTicks()--
Our getTotalTicks() method returns the number of ticks in the AnimationModelImpl. This provides the user a way of checking how long, arbitrarily, the animation will be.

--getShapesAtTick()--
Our getShapesAtTick() method will create a new container of shapes with modified values to be evaluated at a given tick. It takes an int indicating the tick against which the shapes and their transformations will be evaluated. The tick is then passed to each shape in the shapeList's getShapeAtTick method which returns itself with modified values. If the return value is null, the shape does not appear at the given tick. All return values that are not null are placed into a new list and then the list is returned.

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
The Shape interface declares the methods that each Shape should provide such that information regarding the Shape necessary for checking for the shape’s existence and type, adding and removing Transformations to the shape, and getting information regarding data held within the Shape is available to the AnimationModelImpl and the user should they decide to re-use the class outside of the AnimationModelImpl.

---------------------------------------------

CHANGES TO SHAPE CLASSES FROM ASSIGNMENT 7:
We realized that our shapes would all be described as having the same type of height and width values as well as position and color values, so to accomodate the input and simplify the design, we merged all of our shapes into a simple ShapeImpl class, and based on the ShapeType enum, arithmetically recalculate values specific to a type of shape from their overall height/width values when we call the data. Still offers the same functionality as before, just changes the way that the data is stored.

---------------------------------------------

--ShapeImpl class--
The ShapeImpl class implements the Shape interface and defines all of the methods described in the Shape interface such that each Shape returns a uniform set of data in all instances to be evaluated by the AnimationModelImpl. Holds attributes for the String name associated with the shape, the layer the shape is on, the height, width, x and y position values, and Color of the Shape represented as 3 RGB values as well as a ShapeType enum so the type of shape can be specified and checked. Holds methods for adding transformations to the shape, getting transformations on the shape, and a method for changing the internal values of the shape based on the current tick of the animation called getShapeAtTick(). Provides get methods for accessing individual fields in the Shape such as getWidth(), getHeight(), getX(), getY(), getR(), getG(), and getB(). We also decided to create two methods getCreateStatement() and getAppearStatement() that return strings providing information about the shape to be used when creating a textual description of the shape. Since these statements appear in different places in our text representation of the animation, we decided to use to specific methods instead of overriding the toString method and putting them in the same place.

--Constructor--
The ShapeImpl constructor takes a String name to be associated with the shape, a ShapeType enum that describes the type of the shape, and a layer that the shape will be displayed on. Sets the initial values of the shape, such as height, width, position (x,y), and color RGB values to 0. Initializes empty list of transformations on construction.

--addTransformation()--
The addTransformation() method takes a Transformation t and checks to see if another Transformation of the same type with overlapping start and end times already exists on the object. If another Transformation of the same type exists in the same time frame, throws an exception. Otherwise, it adds the transformation to the list of transformations on the Shape.

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
Takes a double tick that represents a certain frame in the animation and modifies the internal values of the shape according to the transformation that is affecting the shape at the specified tick. Returns itself. If the shape does not appear or has no transformations, returns a null value.

--getCreateStatement()--
Returns a String indicating the values of the shape when it first appears on screen.

--getAppearStatement()--
Returns a String indicating when the shape appears and disappears on String.

---------------------------------------------

--ShapeType enum--
The ShapeType enum describes 6 types of shapes implemented in our model: CIRCLE, OVAL, ELLIPSE, RECTANGLE, SQUARE, and TRIANGLE. The purpose of this enum is to allow the ShapeImpl class to differentiate between different types of shapes that it can store. This is used for type-checking in the Views so that the view may construct and modify the values in the shape as necessary to produce the correct output. For instance, in the SVG view, the ellipse requires a radius, but we store the overall height of the ellipse. AS such, by checking that the ShapeType is an ELLIPSE, we can appropriately make the necessary arithmetic changes to reflect the height as a vertical radius.

---------------------------------------------

--Transformation interface--
The Transformation interface describes the methods necessary to get information pertaining to the Transformation via get methods.

----------------------------------------------

CHANGES TO TRANSFORMATIONS FROM ASSIGNMENT 7:
We realized that the way our data was being presented to us was more uniform than we had anticipated, and our prior design with dedicated classes for each transformation wasn't flexible enough to handle the input. So, we consolidated the different classes into a single TransformationImpl class and stored the changes as raw data and calculate the difference between starting and end values when the data is called rather than parsing and analyzing the data when it is stored.

----------------------------------------------

--TransformationImpl class--
The TransformationImpl class implements the Transformation interface and defines the method bodies declared in the interface. Stores the start and end time of the transformation as well as the start and end values for a shapes x position, y position, width, height, and rgb color values. Provides get methods for all informaton in order to evaluate what's being changed over the course of the transformation and to adequately modify the shape that the transformation belongs to and a toString() override method that provides a String representation of the transformation.

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

--AnimationView interface--
Our AnimationView interface declares the methods that must in some sense be implemented by every view. These include openView(), which initializes the view, closeView(), which closes the view, run(), which runs the animation, and drawNewFrame(), which takes a list of shapes and draws them to the screen. Also provides a public static method available to all views called DisplayErrorMessage, which uses JOptionPane to display an error message to the screen.

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
Throws an UnspportedOperationException since text views do not draw frames to the screen.

-----------------------------------------------

--TextViewImpl class--
The TextViewImpl class writes a textual description of the animation to either a specified output .txt file or to System.out. Has two constructors since a fileName does not need to be supplied to create a TextView. Extends the AbstractTextView class.

--TextViewImpl single argument constructor--
The TextViewImpl constructor that takes a single argument takes a String indicating the name of a file that the text output will be written to. If the fileName is null, throws IllegalARgumentException since the file must exist in this instance. If the filename does not end with ".txt", throws IllegalArugmentException since the class must write to a text file if it writes to a file. If the filename is less than 5 characters, including the ".txt", then it does not contain a valid filename, and throws IllegalArgumentException. Passes fileName to the super constructor.

--TextViewImpl no argument constructor--
The TextViewImpl constructor that takes no arugments passes a null fileName value to the super constructor. This will be used to indicate that the textual view should write its output to System.out, the default output for textual views. 

--run() method--
Takes an AnimationModel object that holds the data representing the animation. Calls openView() and closeView() internally to guaruntee that the file is opened and closed each time the run() operation is called. If the output of for the text view is an instance of FileWriter, casts 'out' up to a FileWriter object and writes the textual representation of the animation to the file. Otherwise, appends the textual representation of the animation to System.out. Throws IllegalArgumentException if the AnimationModel is null. Throws IllegalStateException if the FileWriter object of System.Out cannot be written to or if the specified file cannot be written to.

-----------------------------------------------

--SVGViewImpl class--
The SVGViewImpl class writes a text description of an animation to an SVG file formatted in as XML. The created file can be played in an internet browser or opened as a text file. Has methods responsible for opening the SVG file, closing the SVG file, and running the animation, which requires writing a String formatted in XML to the SVG file. Extends the AbstractTextView class.

--Constructor--
The SVGViewImpl constructor takes a String representing a path and file name of the SVG file that the XML format will be written to and an int delay that represents the delay in milliseconds between each tick in the transformation. Throws an IllegalArgumentException if the fileName String is null. Also throws an IllegalArgumentException if the specified file doesn't end with the '.svg' extension or if the name of the file is less than 5 characters, including the '.svg' extension (must have a valid filename). Throws IllegalArgumentException if the specified delay is less than 1.

--run() method--
Takes an AnimationModel m that holds data that represents an animation over a specified number of ticks. Formats the list of Shapes in the AnimationModel in XML and then formats the list of instructions on each of the Shapes in the AnimationModel in XML and then writes the final formatted string to the SVG file. Calls openView() before writing to the file and closeView() after the file has been written to to guaruntee that the SVG file is properly opened and closed each time run() is called. Throws IllegalArgumentException if AnimationModel parameter is null. Throws IllegalStateException if the SVG file cannot be written to.

-----------------------------------------------

--VisualViewImpl class--
The VisualViewImpl class is responsible for creating a visual view that can display an animation on screen. Extends the JFrame class and implements the AnimationView interface. Initializes a frame based on values supplied from the AnimationModel and contains methods for displaying the frame, closing the frame, and running the animation.

--VisualViewImpl constructor--
The VisualViewImpl constructor takes an int x and y values inicating the top left corner of the screen, an int windowWidth and windowHeight indicating the width and height of the window, an int maxWidth and maxHeight indicating the max width and height of the animation, and an in ticksPerSecond that indicates how many ticks pass in a second. If either widths or heights are less than or equal to 0, throws an IllegalArgumentException. IF x or y is less than 0, throws IllegalArgumentException so window cannot be instantiated off the screen. If the ticksPerSecond is less than 1, throws IllegalArgumentException. Creates a ShapePanel object that the shapes will be drawn to, places the ShapePanel in a JScrollPane class, and places the JScrollPane into the current JFrame class. Sets the width and height of the window to windowWidth and windowHeight, sets width and height of the ShapePanel to the maxWidth and maxHeight class so scroll bars can be used to view entire animation. Sets default close operation on JFrame to exit on close.

--openView() method--
Makes the Display window visible.

--run() method--
The run method takes an AnimationModel object to be passed to an AnimationRunner class. Creates a new instance of the AnimationRunner class and passes the AnimationModel parameter as the model, itself as the view, and the ticks per second stored in the class. Calls runAnim() on the AnimationRunner instance to play the animation. Throws an IllegalArgumentException if the AnimationModel parameter is null since the AnimationRunner cannot produce a visual without it.

--closeView() method--
Makes the Display window invisible and removes the window from the current class.

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

--AnimationRunner class--
The AnimationRunner class contains operations for determining the frames per tick, starting the animation, checking the state of the animation, and then closing the animation. Extends the ActionListener class and implements the JSwing timer class. Passes itself to JSwing timer. Takes a value in ticks per second and converts it to frames per tick and runs at approximately 60 frames per second while still reflecting the specified speed of the animation in ticks. Handles running the animation. Is only public for testing purposes, otherwise will be made package protected since it should be internal to the visual view.

--Constructor--
The AnimationRunner constructor takes an AnimationModel, an AnimationView that will display the shapes at each frame to the screen, and an int value ticksPerSecond. Converts the ticks per second into ticksPerFrame. Sets the delay for the timer stored in the class to milliseconds per frame, which is found by dividing 1000/60 to ensure the animation always plays at approximately 60 frames per second.

--runAnim() method--
The runAnim() method opens the view and starts the timer that will call the actionPerformed method in the current listener.

--actionPerformed() method--
The actionPerformed() method keeps track of the number of frames that have passed. Determines if the number of frames surpasses the number of ticks in the animation, and once the animation has run, stops the timer and closes the view. Otherwise, increments the frames and draws a new frame according to the values of the shapes at the given frame in the AnimationModel to the view.

------------------------------------------------

--EasyAnimator class--
The EasyAnimator class contains a static Main method that runs the application. Parses command line arguments and initializes the AnimationModel and the AnimationView according to the command line arguments. If invalid command line arguments are supplied or there are missing command line arguments, uses the static displayAnimationView from AnimationView interface to display an error message to the screen. Is responsible for letting the model and the view communicate with one another. 

--main() method--
A static method that creates an EasyAnimator object that lets an AnimationModel and AnimationView communicate with one another to create an animation. Parses command line arguments and uses them to populate the AnimationModel with data representing an aanimation and creating a specific Textual, SVG, or Visual view. Runs the animation.