package com.fortaleza.app.data.model

object DataProvider {

    val contacts = listOf(
        Contact("🏛️", "Secretaria de Urbanismo", "Urbanismo", "(85) 3452-1000",
            "Av. Santos Dumont, 5335 - Aldeota, Fortaleza - CE"),
        Contact("🏥", "Secretaria Municipal de Saúde", "Saúde", "(85) 3452-1200",
            "Av. Almirante Maximiano da Fonseca, s/n - Fortaleza - CE"),
        Contact("📚", "Secretaria de Educação", "Educação", "(85) 3452-1400",
            "Av. Carneiro de Mendonça, 1600 - Aldeota, Fortaleza - CE"),
        Contact("💧", "CAGECE – Água e Esgoto", "Saneamento", "(85) 0800-275-0195",
            "Av. Mister Hull, s/n - Fortaleza - CE"),
        Contact("⚡", "ENEL – Energia Elétrica", "Energia", "(85) 0800-285-0196",
            "Av. Santos Dumont, 2800 - Aldeota, Fortaleza - CE"),
        Contact("🗣️", "Ouvidoria Municipal", "Geral", "(85) 156",
            "Av. Desembargador Moreira, 380 - Fortaleza - CE"),
        Contact("🚔", "Guarda Municipal", "Segurança", "(85) 3257-7007",
            "R. do Rosário, 255 - Centro, Fortaleza - CE"),
        Contact("🚌", "ETUFOR – Transporte Urbano", "Transporte", "(85) 3101-6540",
            "Av. Aguanambi, 282 - Fortaleza - CE"),
        Contact("🌿", "Sec. de Meio Ambiente", "Ambiente", "(85) 3452-1600",
            "Av. Rui Barbosa, 1246 - Aldeota, Fortaleza - CE"),
        Contact("🏠", "Sec. de Habitação", "Habitação", "(85) 3452-1700",
            "Av. Santos Dumont, 7700 - Fortaleza - CE"),
        Contact("💼", "Sec. de Trabalho e Renda", "Trabalho", "(85) 3452-1800",
            "R. do Rosário, 100 - Centro, Fortaleza - CE"),
        Contact("🧹", "COMAM – Coleta de Lixo", "Limpeza", "(85) 3452-1900",
            "R. Pedro Borges, 33 - Centro, Fortaleza - CE")
    )

    val healthUnits = listOf(
        HealthUnit(
            number = 1,
            name = "Posto de Saúde Miriam Porto Mota",
            address = "R. Cel. Jucá, 1636 - Dionísio Torres, Fortaleza - CE",
            hours = "Seg–Sex: 07h–17h",
            isOpen = true,
            phone = "8534521234",
            lat = -3.7392,
            lng = -38.5107
        ),
        HealthUnit(
            number = 2,
            name = "UBS Presidente Kennedy",
            address = "R. Virgílio Brigido, s/n - Presidente Kennedy, Fortaleza - CE",
            hours = "Seg–Sex: 07h–19h",
            isOpen = true,
            phone = "8534521235",
            lat = -3.7718,
            lng = -38.5461
        ),
        HealthUnit(
            number = 3,
            name = "UBS Barra do Ceará",
            address = "Av. Leste Oeste, s/n - Barra do Ceará, Fortaleza - CE",
            hours = "Seg–Sex: 07h–17h",
            isOpen = false,
            phone = "8534521236",
            lat = -3.6920,
            lng = -38.5880
        )
    )
}
