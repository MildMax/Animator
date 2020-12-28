# Animator
Repository for animator project for 5004
README

Installation:
1) Clone the repo/download the zip file
2) Navigate into Animator
3) Give command "java -jar Animator.jar \<args\>"

  NOTE: Requires "-in" and "-view" args

Arguments are as follows:

-in \<file\> -> input file. These are given in the same directory as the jar file (buildings.txt, big-bang-big-crunch.txt, hanoi.txt, toh-3.txt, toh-5.txt toh-8.txt, toh-12.txt)
  
-out \<file\> -> If 'svg' or 'text' views are chosen, specifies name of file where the animation will be written
  
-view \<view\> -> 'playback' gives video player with controls on screen, 'visual' plays animation in window without playback controls, 'svg' creates svg file to be viewed in browser, 'text' creates .txt file with textual representation of animation
  
-speed \<speed\> -> the speed at which the animation will be played, only relevant to 'svg' and 'visual' views, must be non-negative, do not recommend playing in excess of 200 fps
  
  
Enjoy the show :)

Final Report:

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
Our AnimationModel interface declares methods necessary for adding shapes and transformations to the model. These are necessary so that the Animator can dynamically create shapes according to the user’s input. The AnimationModel also defines several getters, including getShapes(), getTransformations(), getTotalTicks(), getShapesAtTick(), getWindowH
