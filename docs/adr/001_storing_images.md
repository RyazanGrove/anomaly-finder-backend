# Storing images in the context of the application
How images are stored in this application and why.

## Table of Contents
- [Purpose](#purpose)
- [Reasoning](#reasoning)
- [Model](#model)


## Purpose
The purpose of this file is to explain what was behind the reasoning of the selection for the approach to store images, which are used by the application.

## Reasoning
Most common databases are designed and optimized to handle structured data and queries efficiently, not large blobs of binary data. It was decided not to store images in a database. By performance reasons was decided to store the metadata about the images in the database and when needed, using this data, client could query the image directly from backend.
For now, for development purposes the images are stored locally in `src/main/resources/images` folder. Later, when the application is deployed to the cloud server it will use different kind of storage to be able to update versions of application correctly and also store custom images from users (this functionality is not implemented yet).
This case increases performance of the system by reducing one unnecessary connection (sending and retrieving image from and to database) in the process.

## Model
Metadata stores the information about the image, which is needed to locate it and work with it.
Image is located by `fileName` and `type` fields. Then frontend uses `imageWidth` and `imageHeight` fields to display the image and also to calculate the ratio needed to correctly handle user clicks on the target area. Currently, the target area represented as a rectangle using `xMin`, `xMax`, `yMin` and `yMax`. But this approach is not the best as it could also cover some area, where the real target is not present, and because of it the approach later will be changed to Ray-Casting algorithm based on Polygons.

The current model of the Image Info (metadata) object:

```
{
    Long id; // generated automatically by database
    String fileName; // file name of the related image
    String type; // file type of the related image
    int imageWidth; // width of the related image
    int imageHeight; // height of the related image
    TargetArea target; // target area to click
}
```
TargetArea has the next model:
```
{
    int xMin; // left border
    int xMax; // right border
    int yMin; // top border
    int yMax; // bottom border
}
```