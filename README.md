# SBT OSGi Demo

A demo project to show how to build a OSGi bundle from a Scala project by using sbt (simple build tool).
For generating the OSGi bundle the sbt OSGi plugin is used which integrates bnd into sbt.

More details see
http://wiki.osgi.org/wiki/SbtScalaBndToolchain

## Build
  
  sbt ~osgi-bndle
  
## Run
Run the OSGi framework on changes the demo bundle will updated.

  sbt run

## LICENSE 
 
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.