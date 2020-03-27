# Animator
Repository for animator project for 5004
README

Animation Model Assignment 7 – Joseph Burns, Chris Williams

This README describes the classes, interfaces and enums used in our Animation Model as well as verbose descriptions of why we made the decisions to design our classes in such a way. 

--AnimationModel interface--
Our AnimationModel interface declares methods necessary for adding and removing shapes and transformations to the model. These are necessary so that the Animator can dynamically create and remove shapes according to the user’s input. The AnimationModel also defines several getters, including getShapes(), getTransformations(), getTotalTicks(), getShapesAtTick(), getWindowHeight(), getWindowWidth(), and getBackgroundColor() so that the state of the AnimationModel can be checked.

---------------------------------------------

--AnimationModelImpl class--
Our AnimationModelImpl class defines the method bodies for the methods declared in the AnimationModel interface. It seeks to provide a snapshot of the information held inside the AnimationModelImpl and to maintain a hierarchical order of Shapes that are responsible for their own transformations.

--Constructors--
In our AnimationModelImpl (the implementation of the model defined in the AnimationModel interface) we provide 4 constructors:
The first constructor is the default constructor: it sets the height and width of the window to 500 and sets the background color to White(0.0,0.0,0.0 in RGB values). We also provide three other constructors that allow the user to define the background color, the window width and height, and both the background color and the window width and height. It does not allow for negative width or height for window size. The constructors also initialize a HashMap to store shapes according to their shapeName string value so that any shape can be accessed in constant time. Also initializes total ticks for the animation to be 0.

--addShape()--
Our addShape() method takes a Shape object and checks to see if a Shape already exists with the same name. If it does not, it adds the shape to the HashMap. If the shape does exist, it throws an exception. This helps maintain a unique set of shapes. Note, shapes may be added to the same layer; it is up to the user or the controller to determine how layers will be assigned. Shapes that exist on the same layer may clash based on the implementation of the view. Layer does not play into whether or not adding a shape is valid.

--removeShape()--
Our removeShape() method takes a String shapeName and tries to find a value in the HashMap corresponding to the shapeName. If it does not exist, it throws an exception. Otherwise, it removes the shape with the associated name from the HashMap so it no longer exists in our Animator.

--addTransformation()--
Our addTransformation() method takes a String shapeName indicating the name of the shape the transformation should be added to and a Transformation to be added to the shape. If the shape does not exist, throws an exception. If the shape does exist, it checks to see if the shape already has a like transformation that occurs in the same time frame. If a transformation of the same type does exist in the same time frame, it throws an exception. Otherwise, it adds the Transformation to the Shape’s list of transformations. It then determines whether or not the end time of the transformation exceeds the current total number of ticks in the AnimationModelImpl, and if it does, sets the total number of ticks to the end time of the transformation.

--removeTransformation()-- 
Our removeTransformation() method takes a String shapeName that indicates the Shape the transformation is on, a TransformationType that indicates the type of transformation, and the start and end times of the transformation to be removed. If the Shape as specified by shapeName doesn’t exist, it throws an exception. If the transformation does not exist, it throws an exception. Then, if the end value matches the total number of ticks in the AnimationModelImpl, it gets a list of all transformations, finds the transformation with the greatest end time, and sets the total number of ticks to that value.

--getShapes()--
Our getShapes() method returns a list of Shapes held in the AnimationModelImpl. The values in our Shapes are all final, so we do not create copies of the shapes as their attributes cannot be changed. We do place the Shapes into a new List so that the HashMap that holds the shapes cannot be modified outside of the class. This provides the user a way of looking at all the shapes inside the AnimationModelImpl.

--getTransformations()--
Our getTransformations() method returns a list of all Transformations on every Shape in the AnimationModelImpl. The attributes inside each transformation are final so we do not return copies since the attributes cannot be changed outside of the model. We do place the Transformations into a new list, though, so no list of transformations inside AnimationModelImpl can be modified outside of the class. This provides the user a way of looking at all the transformations that will happen in the animation.

--getTotalTicks()--
Our getTotalTicks() method returns the number of ticks in the AnimationModelImpl. This provides the user a way of checking how long, arbitrarily, the animation will be.

--getShapesAtTick()--
Our getShapesAtTick() method will create a new container of shapes with modified values to be evaluated at a given tick. It takes an int indicating the tick against which the shapes and their transformations will be evaluated. Since we are unsure currently of how the controller or the view will be evaluating these Shapes, we have left the method body blank so we can adapt our design to the specifications of the other aspects of the overall design. This may require adding additional methods to our Shape and Transformation interfaces, though any changes will be a direct reflection of what occurs in this method. 

--getWindowHeight()--
Our getWindowHeight() method will return an int indicating the height of the window.

--getWindowWidth()--
Our getWindowWidth() method will return an int indicating the width of the window.

--getBackgroundColor()--
Our getBackgroundColor method will return a Color indicating the background color of the window. 

--toString()--
Our toString() override method creates a verbose description of the window size and color, and a list of shapes with each shape’s respective transformations in chronological order by start time below the shape’s description. We decided to format our verbose description in this way so each Shape has its transformations associated with it in a visually simplistic way. As the list of Shapes and Transformations gets large, finding a particular transformation associated with a shape would become difficult. By keeping the Transformations on a shape directly below each shape’s description, a user can more efficiently evaluate all of the transformations occurring on that particular shape. This can be used for debugging purposes later on. 

---------------------------------------------

--Color class--
The Color class simply holds three RGB values as doubles with a value between 0.0 and 1.0. It provides get methods to access the RGB values individually and a toString method that provides a string representation of the values such that “R,G,B” is “0.0,0.5,1.0” when R is 0.0, G is 0.5 and B is 1.0. All attributes are final and may not be changed.

--Constructor--
The Color constructor takes three floating point values indicating the RGB values of the color. The floating point values may not be less than 0 or greater than 1, otherwise it throws an exception.

--getR()--
The getR() method returns the red value for the color. Provides a way of getting information associated with the color.

--getG()--
The getG() method returns the green value for the color. Provides a way of getting information associated with the color.

--getB()--
The getB() method returns the blue value for the color. Provides a way of getting information associated with the color.

--toString()--
The toString() override method provides a String description of the color such that “R,G,B” is “0.0,0.5,1.0” when R is 0.0, G is 0.5 and B is 1.0. 

---------------------------------------------

--Shape interface--
The Shape interface declares the methods that each Shape should provide such that information regarding the Shape necessary for checking for the shape’s existence and type, adding and removing Transformations to the shape, and getting information regarding data held within the Shape is available to the AnimationModelImpl and the user should they decide to re-use the class outside of the AnimationModelImpl.

---------------------------------------------

--AbstractShape class--
The Abstract Shape class implements the Shape interface and defines all of the methods described in the Shape interface such that each Shape returns a uniform set of data in all instances to be evaluated by the AnimationModelImpl. Holds attributes for the String name associated with the shape, the layer the shape is on, the initial height, width, x and y position values, and Color of the Shape at the start of the animation. Also holds a list of transformations on the Shape, the type of the shape used for type-checking, and the transparency of the shape. Note, we chose to define the position of the Shape by its center since all shapes have a center value, but not all shapes have a corner value. Those shapes that utilize a corner value, such as a Rectangle or a Triangle, can be calculated using width and height dimensions and the center value. Until we know how the controller and the view will handle displaying these shapes, we have left all Shape objects to define their position per their center. Note, the type associated with the shape exists to be used in the future so that any transformations specific to a type can be checked and then casted up into the Shape’s concrete type to access any methods specific to that shape. Until we know how Transformations should affect and produce new Shape values, type is only used to ensure type is properly assigned in testing.

--Constructor--
The AbstractShape constructor takes a String name to be associated with the shape, the initial height and width of the shape, the initial center x and y positions of the shape, the Color of the shape, and the type of the shape. The ShapeType will be defined in the concrete classes, and is not set by the user. Initializes an ArrayList to hold Transformations that will be added to the Shape. Sets the initial transparency of the Shape to 0.0 so it can be modified by Appearance and ChangeTransparency transformations as necessary.

--addTransformation()--
The addTransformation() method takes a Transformation t and checks to see if another Transformation of the same type with overlapping start and end times already exists on the object. If another Transformation of the same type exists in the same time frame, throws an exception. Otherwise, it adds the transformation to the list of transformations on the Shape.

--removeTransformation()--
The removeTransformation() method takes a TransformationType and a start and end time indicating the start and end of the Transformation. If the Transformation does not exist, throws an exception. Otherwise, it removes the element from the list of Transformations on the object.

--getName()--
The getName() method returns the String name associated with the Shape. Is used in AnimationModelImpl to check if a shape already exists and to create a key in the HashMap that corresponds to the Shape.

--getType()--
The getType() method returns the type of the Shape. This will be used for type checking when we implement the controller and the view. Since certain shapes will be modified differently (for example, a Circle can’t modify height and width to be different values), getting the type of the shape will be important to ensuring the transformations that occur on it will reflect the constraints of the Shape. We use this to avoid using reflection to check the type of the class, and eliminate the overhead associated with reflection.

--getTransformationDescription()--
The getTransformationDescription() method returns verbose String description of the Transformations on a particular shape. It does not reflect the initial values of the Shape, and instead provides a description of just the Transformations, should the user or the AnimationModelImpl require such a description. Is not put in the toString() method since it creates a description of objects held within the Shape rather than aspects of the Shape itself. 

--getTransformationList()--
The getTransformationList() method creates a new list that consists of the same elements in the list of transformations in the Shape and returns the new list. The elements in the list, the Transformations, all have final attributes so they may not be changed, and are thus added directly rather than creating copies of each transformation. We place the Transformations in a new list, though, so that the list inside of the Shape object cannot be modified outside of the Shape object. Any changes to the list will be reflected in the returned list, not the Transformation list in Shape.

---------------------------------------------

--Circle class--
The Circle class has a constructor that reflects the values associated with a Circle, so that different height and width cannot be defined for the Circle as well as a toString() override method that reflects the initial values of the Circle. Extends the AbstractShape class which implements the Shape interface.

--Constructor--
The constructor for the Circle class takes a String name to be associated with the object, a layer, a radius, an initial x and y position values, and a Color the Circle will take on. Stores the radius in its own field to be used later when using transformations to modify the Shape’s attributes in some way for the view and controller. Passes the ShapeType.Circle type to the super constructor so the Circle class can be identified as a circle without using reflection.

--toString()--
The toString() override method provides a unique verbose String description of a circle to reflect its radius as well as its attributes regarding position, color and layer. Also produces a list of Transformations as a String that are associated with the Circle.

---------------------------------------------

--Oval class--
The Oval class has a constructor that reflects the values associated with an oval, so the height and width values are calculated from the vertical and horizontal radius of the Oval. Also contains a toString() override method that reflects these values specific to the Oval. Extends the AbstractShape class which implements the Shape interface.

--Constructor--
The Oval constructor takes a String name to be associated with the Oval, vertical and horizontal radii, the initial x and y values indicating the position of the oval, and a Color that the Oval takes on initially. Calculates the width and height from the radii and passes those values to the super constructor, also passes ShapeType.Oval to the super constructor so that the Oval type may be checked later on when calculating modified values at each tick in the animation.

--toString()--
The toString() override method provides a unique description of the Oval according to its specific values (radii v height and width) and other starting values as well as a verbose String of Transformations on the oval.

---------------------------------------------

--Rectangle class--
The Rectangle class has a constructor that takes its initial values and passes them directly to the super constructor in addition to its own ShapeType so that the object may be identified as a Rectangle without using reflection to check the class type. Also contains a toString() method that reflects these values as they pertain to a Rectangle. Extends the AbstractShape class which implements the Shape interface.

--Constructor--
The Rectangle constructor takes a String name to be associated with the Rectangle, a layer, initial height and width of the Rectangle, initial x and y center positions of the Rectangle, and an initial Color that the Rectangle will take on. Passes all values directly to super constructor as well as ShapeType.Rectangle so that the Rectangle may be identified as a Rectangle.

--toString()--
The toString() override method provides a verbose String description of the values associated with the Rectangle as well as verbose list represented as a String of Transformations on the Rectangle.

---------------------------------------------

--Square class--
The Square class has a constructor that takes the values specific to a Sqaure (one width/height parameter so they cannot be defined separately) and passes its side length to the width and height parameters in the super constructor as well as a ShapeType that can be used to identify the object as a Square. Also contains a toString() method that reflects the specific attributes of a Square. Extends the AbstractShape class which implements the Shape interface.

--Constructor--
The Square constructor takes a String name to be associated with the shape, a layer the Square will exist on, a side value that will represent the height and width of the Square in the super constructor, and values indicating the initial x and y positions of the center of the Square, and a Color indicating the initial color of the Square. Stores the side length of the Square in its own field to be used by the Square class later on. Passes its ShapeType.Square directly to super constructor so class can be identified as a Square later on.

--toString()--
The toString() override method provides a verbose String description of the values specific to a Square (one side length, not a height and width of the same value) as well as a list of Transformations as a String associated with the Square.

---------------------------------------------

--Triangle class--
The Triangle class has a constructor that takes the values associated with a generic Shape and passes its ShapeType to the super constructor so it may be identified as a Triangle without using reflection. Also contains a toString() method that reflects the attributes of a Triangle. Extends the AbstractShape class which implements the Shape interface.

--Constructor--
The Triangle constructor takes a String name to be associated with the Shape as well as a layer, initial height, initial width, and x and y center values of the shape, as well as an initial Color that the Triangle will take on. Passes all values to super constructor as is, as well as ShapeType.Triangle so that the Shape may be identified as a Triangle later on.

--toString()--
The toString() override method describes the attributes of the Triangle as well as a verbose list of Transformations as a String associated with the Triangle.

---------------------------------------------

--ShapeType enum--
The ShapeType enum describes 5 types of shapes implemented in our model: CIRCLE, OVAL, RECTANGLE, SQUARE, and TRIANGLE. The purpose of this enum is to allow Shapes to pass their ShapeType from their concrete class to their super class so that the type of each Shape can be checked without using reflection(i.e. instanceof, obj.getClass()) which requires a substantial amount of overhead. This way, we can guarantee how the types of shapes will be checked. We deemed this necessary since different transformations affect different attributes of each Shape in a unique manner, and in order to make the correct modifications to Shape objects over the course of the animation, we need to know what shape we will be modifying so we modify it properly. This way, we don’t modify the vertical radius of an Oval the same way we modify the height of a Rectangle, since a radius is half the diameter of an oval while the height of the Rectangle describes its total height already.

---------------------------------------------

--Transformation interface--
The Transformation interface describes the methods necessary to get information pertaining to every type of Transformation. Provides getStart() to get the start tick of the Transformation, getEnd() to get the end tick of the Transformation, and getType() to check the type of the Transformation. Until we know how Transformations will affect Shapes over the course of the animation, our get methods are primarily designed to ensure that erroneous, duplicate, or otherwise conflicting Transformations are not added to Shapes in a way that would cause an issue with the view. Also provides a way of checking what Transformations are affect the state of a Shape at a particular tick by comparing the tick value against start and end times of a particular Transformation. Note: Until we know how the controller and the view seeks to change the objects, we have not inserted any methods that would change a Shape’s value according to the transformation, just attributes that can be used later on to transform a shape’s properties according to the type of transformation. For now, all methods are used for checking to make sure conflicting Transformations are not added to the AnimationModel or a Shape.

---------------------------------------------

--AbstractTransformation class--
The AbstractTransformation class implements the Transformation interface and defines the method bodies declared in the interface. Also stores attributes such as startTime, endTime and TransformationType that are used by all Transformations. 

--Constructor--
The AbstractTransformation constructor takes a start time for the Transformation, an end time for the Transformation, and a TransformationType type that describes the type of Transformation. Throws an exception if the start time is less than 0 or if the end time less than start or if the TransformationType is null. 

--getStart()--
The getStart() method returns the start tick of the Transformation. Is used to check if other Transformations exist in the same time frame as the current Transformation in AnimationModelImpl and AbstractShape. Will also be used to calculate the modified value of a shape according to a specified tick by giving a frame of reference in relation to the type of change.

--getEnd()--
The getEnd() method returns the end tick of the Transformation. Is used to check if other Transformations exist in the same time frame as the current Transformation in AnimationModelImpl and AbstractShape. Will also be used to calculate the modified value of a Shape according to a specified tick by give a frame of reference in relation to the type of change.

--getType()--
The getType() method returns the enum TransformationType of the Transformation. Is used to check the type of the transformation without using reflection (i.e. instanceof, obj.getClass()) to reduce overhead. Is used to check type when adding transformations to a Shape.

--toString()--
The toString() method returns a String that indicates the time frame in which the Transformation is used. Will be used in concrete classes to generate a String that describes the entire Transformation according to specific criteria of the transformation, including values and type. 

---------------------------------------------

--Appearance class--
The Appearance class describes the points at which an object appears and disappears during the animation. An object may appear and disappear multiple times (for instance, to make an object blink across the screen), and thus multiple Appearance Transformations may be added to a single Shape object. To increase functionality, the user may define Transformations that occur while the object is “disappeared”, though this does require the user to be more careful about adding Appearance in order to make the objects appear. Sets Transparency of object to 0 or 100 instantaneously, Transparency may also be modified to a different value between and including these values in ChangeTransparency. Extends the AbstractTransformation class which implements the Transformation interface.

--Constructor--
The Appearance constructor takes a start time and an end time at which the object will appear and disappear on screen. Passes these values to the super constructor for checking as well a TransformationType.Appearance so that the Transformation may be identified as an Appearance without using reflection to check class types.

--toString()--
The toString() override method returns a String indicating the time at which the transformation is set to make the object appear and disappear.

---------------------------------------------

--ChangeColor class--
The ChangeColor class describes the times at which a Shape will change color as well as the color that the Shape will be changing to. Stores the new Color in the concrete class. Extends the AbstractTransformation class which implements the Transformation interface.

--Constructor--
The constructor for ChangeColor takes a start and an end time at which the color on a shape will begin to change as well as a Color object that describes the Color the Shape will be changing too. Throws an exception if the Color is null. Passes start and end times to the super constructor as well as TransformationType.CHANGECOLOR so that the Transformation can be determined to be ChangeColor without using reflection.

--toString()--
The toString() override method returns a String that describes the Color that the Shape will be changing to as well as the time in which the change will be occurring from the super toString() method.

---------------------------------------------

--ChangeHeight class--
The ChangeHeight class describes the times at which a Shape will change its height as well as the new height that the Shape will take on. Stores the new height in its concrete class. Extends the AbstractTransformation class which implements the Transformation interface.

--Constructor--
The constructor for ChangeHeight takes a start and an end time that indicate the start and end of the Transformation as well as a new height that that the Shape’s dimensions will be taking on. Stores new height in its own newHeight field and passes the start and end time to the super constructor along with the TransformationType.CHANGEHEIGHT so that the type of the Transformation can be checked without reflection.

--toString()--
The toString() override method returns a String that describes the new height the Shape’s dimensions will be taking on as well as the time frame in which the Transformation will be occurring over, which it gets from the super toString() method.

---------------------------------------------

--ChangeWidth class--
The ChangeWidth class describes the times at which a Shape will change its width as well as the new width that the Shape will take on. Stores the new width in its concrete class. Extends the AbstractTransformation class which implements the Transformation interface. Will operate very similarly to ChangeHeight, save for the attribute it changes. 

--Constructor--
The constructor for ChangeWidth takes a start and an end time that indicate the start and end of the Transformation as well as a new width that that the Shape’s dimensions will be taking on. Stores new width in its own newWidth field and passes the start and end time to the super constructor along with the TransformationType.CHANGEWIDTH so that the type of the Transformation can be checked without reflection.

--toString()--
The toString() override method returns a String that describes the new width the Shape’s dimensions will be taking on as well as the time frame in which the Transformation will be occurring over, which it gets from the super toString() method.

---------------------------------------------

--ChangeTransparency class--
The ChangeTransparency class describes the time frame in which a Shape will change it’s Transparency as well as the new level of transparency in the object. Sets Transparency to exist on a scale from 0 to 100, where 0 is invisible and 100 is totally visible. Affects the same attribute as Appearance, but can be used in a more specific manner, creating changes in Transparency over a period of time instead of instantaneously. Offers more functionality than Appearance in terms of changing transparency, but requires specific instructions (Same start and end, transparency set to 0 or 100) to produce the same functionality. Extends the AbstractTransformation class which implements the Transformation interface.

--Constructor--
The ChangeTransparency constructor takes a start and end time for the Transformation as well as a new target transparency that the Shape will be changed to over the specified time frame. The new Transparency value cannot be less than 0 or greater than 100. Throws an exception if the transparency value is less than 0 or greater than 100. Passes the start and end times to the super constructor as well as TransformationType.CHANGETRANSPARENCY so that the type of the transformation may be checked without using reflection. 

--toString()--
The toString() override method returns a String that describes the new transparency that the Shape will take on as well as the time frame the Transformation takes place, which it gets from the super toString() method.

---------------------------------------------

--Move class--
The Move class describes the time frame in which an object will move to a location specified by an x and y coordinate value that corresponds to a place on or off of the screen. The x and y values can also be negative, as we assume that the view describes the window as a Cartesian Coordinate Plane with some respect to origin (0,0). Extends the AbstracTransformation class, which implements the Transformation interface.

--Constructor--
The Move constructor takes a start and end time at which the Move transformation will occur over as well as an x and y coordinate value that indicates the position on or off the screen that the Shape will move to during the Transformation. Stores the x and y coordinate values of the Move in the concrete class. Passes the start and end times of the Transformation to the super constructor as well as TransformationType.MOVE so that the Transformation can be identified without using reflection.

--toString()--
The toString() override method provides a String that describes the position the Shape will be moving to as well as the time frame over which the Move will occur, which it gets from the super toString() method.

---------------------------------------------

--Scale class--
The Scale class describes the time frame in which an object will be scaled to a size specified by a floating point value. The new value that the Shape will be scaled to cannot be less than or equal to 0, such as to make the shape disappear in an invalid manner. Extends the AbstractTransformation class, which implements the Transformation interface.

--Constructor--
The Scale constructor takes a start and end time at which the Scale Transformation will occur as well as a floating point value that describes the scale to which the Shape will be modified. 0.5 would reduce the shape’s size by half, whereas 2.0 would double its size. There is no limit on how large the shape can be made, as we do not know the scale of the screen according to the view or the controller yet. Stores the new scale value in its own field and passes the start and end times to the super constructor as well as TransformationType.SCALE so the type of the Transformation can be determined without reflection.

--toString()--
The toString() override method provides a String that describes the scale value the Shape will be modified by as well as the time frame over which the scaling will occur, which it gets from the super toString() method.

---------------------------------------------

--TranformationType enum--
The TransformationType enum describes the different types of Transformations that can be applied to a Shape. We use the enum to check types so that we can avoid using reflection (i.e., instanceof, obj.getClass()) and reduce overhead when checking to see if other Transformations already exist on a shape. This way of type checking transformations will also be important when modifying new values for Shapes over the course of the animation according to the Transformation. Scale does not change a Shape’s values the same way changing its color does, so having the ability to easily check one transformation against another will make implementing some kind of method that provides modified Shapes at a particular tick in the animation much easier. 
