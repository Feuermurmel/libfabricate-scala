# LibFabricate

LibFabricate, (soon-to-be) Scala library to create projects for 2D and 3D modelling with a programmatic API in Scala.


## sbt Project Structure

- `libfabricate`: Not-yet-existing main library providing the most abstract APIs to interact with the different modelling environments.

- `libopenscad`: API to create OpenSCAD files using a Scala API and interact with OpenSCAD in a programmatic way.

    Currently, the APIs in this sub-project allow creation of OpenSCAD files with a subset of the available 2D and 3D operations in OpenSCAD.

- `libopenscad_demo`: Runnable sub-project which showcases the API provided by `libopenscad`.

    The project can be run from `sbt`. This will write sample OpenSCAD files to `libopenscad_demo/output`:
    
    ```
    $ sbt
    [...]
    sbt:libfabricate-scala> libopenscad_demo/run
    [info] Running Main 
    [success] Total time: 1 s, completed Feb 19, 2019 7:34:38 PM
    ```
