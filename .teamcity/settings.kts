import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2018.2"

project {

    vcsRoot(HttpsGithubComRawlingssamSpringPetclinicGitRefsHeadsMaster)

    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(HttpsGithubComRawlingssamSpringPetclinicGitRefsHeadsMaster)
    }

    steps {
        maven {
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = defaultProvidedVersion()
        }
    }

    triggers {
        vcs {
        }
    }
})

object HttpsGithubComRawlingssamSpringPetclinicGitRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/rawlingssam/spring-petclinic.git#refs/heads/master"
    url = "https://github.com/rawlingssam/spring-petclinic.git"
    authMethod = password {
        userName = "rawlingssam"
        password = "credentialsJSON:8da3979e-40fa-4ce7-9594-64671bb8e24f"
    }
})
