import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSetContainer
import java.io.File
import java.net.URLClassLoader

val pathToRoot = "no.nav.helse.flex.sykepengesoknad.normalisert.SoknadDTO"

class GenerateJsonSchemaPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("generateJsonSchema") {
            group = "build"
            description = "Generates JSON schema for sealed interface"
            dependsOn("classes")
            doLast {
                val sourceSets = project.extensions.getByName("sourceSets") as SourceSetContainer
                val mainOutput = sourceSets.getByName("main").output.classesDirs
                val classLoader =
                    URLClassLoader(
                        mainOutput.map { it.toURI().toURL() }.toTypedArray(),
                        this.javaClass.classLoader,
                    )
                val clazz = classLoader.loadClass(pathToRoot)

                val mapper =
                    ObjectMapper()
                        .registerModule(KotlinModule.Builder().build())
                        .enable(SerializationFeature.INDENT_OUTPUT)

                val generator = JsonSchemaGenerator(mapper)
                val schema = generator.generateSchema(clazz)

                val outputFile = project.buildDir.resolve("schemas/SoknadDTO.json")
                outputFile.parentFile.mkdirs()
                mapper.writeValue(outputFile, schema)

                println("Schema generated at: ${outputFile.absolutePath}")
            }
        }
    }
}
