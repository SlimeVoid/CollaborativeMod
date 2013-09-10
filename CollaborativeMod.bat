@echo off

set programdir="C:\Programming"
set packagedir="%programdir%\Packages"
set repodir="%programdir%\Repositories"
set forgedir="%repodir%\MinecraftForge"
set fmldir="%repodir%\MinecraftForge\fml"
set mcpdir="%forgedir%\mcp"
set projectbench="%repodir%\ProjectBench"
set euryscore="%repodir%\EurysCore-FML"
cd %mcpdir%

if not exist %euryscore% GOTO :PBFAIL
if exist %projectbench% GOTO :PROJECTBENCH
GOTO :PBFAIL

:PROJECTBENCH
if exist %mcpdir%\src GOTO :COPYSRC
GOTO :PBFAIL

:COPYSRC
if not exist "%mcpdir%\src-work" GOTO :CREATESRC
GOTO :PBFAIL

:CREATESRC
mkdir "%mcpdir%\src-work"
xcopy "%mcpdir%\src\*.*" "%mcpdir%\src-work\" /S
if exist "%mcpdir%\src-work" GOTO :COPYPB
GOTO :PBFAIL

:COPYPB
xcopy "%euryscore%\SV-common\*.*" "%mcpdir%\src\minecraft\" /S
xcopy "%projectbench%\CM-source\*.*" "%mcpdir%\src\minecraft\" /S
pause
call %mcpdir%\recompile.bat
call %mcpdir%\reobfuscate.bat
echo Recompile and Reobf Completed Successfully
pause

:REPACKAGE
if not exist "%mcpdir%\reobf" GOTO :PBFAIL
if exist "%packagedir%\CollaborativeMod" (
del "%packagedir%\CollaborativeMod\*.*" /S /Q
rmdir "%packagedir%\CollaborativeMod" /S /Q
)
mkdir "%packagedir%\CollaborativeMod\slimevoid\collaborative"
xcopy "%mcpdir%\reobf\minecraft\slimevoid\collaborative\*.*" "%packagedir%\CollaborativeMod\slimevoid\collaborative\" /S
xcopy "%projectbench%\CM-resources\*.*" "%packagedir%\CollaborativeMod\" /S
echo "Project Bench Packaged Successfully
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
GOTO :PBCOMPLETE

:PBFAIL
echo Could not compile Project Bench
GOTO :PBEND

:PBCOMPLETE
echo Project Bench completed compile successfully
GOTO :PBEND

:PBEND
pause