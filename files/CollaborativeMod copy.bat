@echo off

set programdir=%CD%\..\..
set repodir="%programdir%\Git"
set packagedir="%programdir%\Packages"
set forgedir="%programdir%\forge"
set fmldir="%forgedir%\fml"
set mcpdir="%forgedir%\mcp"
set collaborative="%repodir%\CollaborativeMod\src\main"
set slimelib="%repodir%\SlimevoidLibrary\src\main\java"
cd %mcpdir%

if not exist %slimelib% GOTO :CMFAIL
if exist %collaborative% GOTO :COLLABORATIVE
GOTO :CMFAIL

:COLLABORATIVE
if exist %mcpdir%\src GOTO :COPYSRC
GOTO :CMFAIL

:COPYSRC
if not exist "%mcpdir%\src-work" GOTO :CREATESRC
GOTO :CMFAIL

:CREATESRC
mkdir "%mcpdir%\src-work"
xcopy "%mcpdir%\src\*.*" "%mcpdir%\src-work\" /S
if exist "%mcpdir%\src-work" GOTO :CMCOPY
GOTO :CMFAIL

:CMCOPY
xcopy "%slimelib%\*.*" "%mcpdir%\src\minecraft\" /S
xcopy "%collaborative%\java\*.*" "%mcpdir%\src\minecraft\" /S
pause
call %mcpdir%\recompile.bat
call %mcpdir%\reobfuscate.bat
echo Recompile and Reobf Completed Successfully
pause

:REPACKAGE
if not exist "%mcpdir%\reobf" GOTO :CMFAIL
if exist "%packagedir%\CollaborativeMod" (
del "%packagedir%\CollaborativeMod\*.*" /S /Q
rmdir "%packagedir%\CollaborativeMod" /S /Q
)
mkdir "%packagedir%\CollaborativeMod\com\slimevoid\collaborative"
xcopy "%mcpdir%\reobf\minecraft\com\slimevoid\collaborative\*.*" "%packagedir%\CollaborativeMod\com\slimevoid\collaborative\" /S
xcopy "%collaborative%\resources\*.*" "%packagedir%\CollaborativeMod\" /S
echo Collaborative Mod Packaged Successfully
pause
ren "%mcpdir%\src" src-old
echo Recompiled Source folder renamed
pause
ren "%mcpdir%\src-work" src
echo Original Source folder restored
pause
del "%mcpdir%\src-old" /S /Q
del "%mcpdir%\reobf" /S /Q
if exist "%mcpdir%\src-old" rmdir "%mcpdir%\src-old" /S /Q
if exist "%mcpdir%\reobf" rmdir "%mcpdir%\reobf" /S /Q
echo Folder structure reset
GOTO :CMCOMPLETE

:CMFAIL
echo Could not compile Collaborative Mod
GOTO :CMEND

:CMCOMPLETE
echo Collaborative Mod completed compile successfully
GOTO :CMEND

:CMEND
pause