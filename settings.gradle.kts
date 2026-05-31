rootProject.name = "mavai-ch"

val newsroomDir = file("../mavai-newsroom")
if (newsroomDir.isDirectory) {
    includeBuild(newsroomDir) {
        dependencySubstitution {
            substitute(module("org.mavai:mavai-newsroom")).using(project(":"))
        }
    }
}

// Use local punit when available (sibling folder), Maven Central otherwise
val punitDir = file("../punit")
if (punitDir.isDirectory) {
    includeBuild(punitDir) {
        dependencySubstitution {
            substitute(module("org.javai:punit-core")).using(project(":punit-core"))
            substitute(module("org.javai:punit-junit5")).using(project(":punit-junit5"))
        }
    }
}

// Use local outcome when available (sibling folder), Maven Central otherwise
val outcomeDir = file("../outcome")
if (outcomeDir.isDirectory) {
    includeBuild(outcomeDir) {
        dependencySubstitution {
            substitute(module("org.javai:outcome")).using(project(":"))
        }
    }
}