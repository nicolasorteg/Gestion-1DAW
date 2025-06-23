package nicolasorteg.gestion1daw.di

import org.koin.dsl.module
import nicolasorteg.gestion1daw.alumno.dao.AlumnoDao
import nicolasorteg.gestion1daw.alumno.repository.AlumnoRepositoryImpl
import nicolasorteg.gestion1daw.alumno.service.AlumnoService
import nicolasorteg.gestion1daw.alumno.service.AlumnoServiceImpl
import nicolasorteg.gestion1daw.alumno.storage.AlumnoStorageCsv
import nicolasorteg.gestion1daw.alumno.validator.AlumnoValidator
import nicolasorteg.gestion1daw.common.viewmodels.PantallaInicialViewModel
import org.jdbi.v3.core.Jdbi

val appModule = module {

    single {
        Jdbi.create("jdbc:h2:file:./data/alumnosdb").also {
            it.installPlugin(org.jdbi.v3.sqlobject.SqlObjectPlugin())
        }
    }

    // DAO
    single<AlumnoDao> {
        get<Jdbi>().onDemand(AlumnoDao::class.java)
    }

    // Repositorio
    single {
        AlumnoRepositoryImpl(get())
    }

    // Servicio
    single<AlumnoService> {
        AlumnoServiceImpl(
            repositorio = get(),
            validator = get(),
            storage = get()
        )
    }

    // storage
    single<AlumnoStorageCsv> {
        AlumnoStorageCsv()
    }

    // validador
    single<AlumnoValidator> {
        AlumnoValidator()
    }


    // ViewModel
    single {
        PantallaInicialViewModel(get())
    }
}