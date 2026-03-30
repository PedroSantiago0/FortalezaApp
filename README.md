# Fortaleza Prefeitura App — Android (Kotlin)

Aplicativo nativo Android para os serviços da Prefeitura de Fortaleza.

---

## Estrutura do Projeto

```
FortalezaApp/
├── app/
│   ├── build.gradle
│   ├── proguard-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/fortaleza/app/
│       │   ├── data/
│       │   │   ├── db/
│       │   │   │   ├── AppDatabase.kt       ← Room Database
│       │   │   │   ├── RecordDao.kt         ← DAO
│       │   │   │   ├── RecordRepository.kt
│       │   │   └── model/
│       │   │       ├── Record.kt            ← Entidade Room
│       │   │       ├── Contact.kt
│       │   │       ├── HealthUnit.kt
│       │   │       └── DataProvider.kt      ← Dados estáticos
│       │   └── ui/
│       │       ├── activities/
│       │       │   ├── SplashActivity.kt
│       │       │   ├── LoginActivity.kt
│       │       │   ├── HomeActivity.kt
│       │       │   ├── ReclameActivity.kt
│       │       │   ├── IptuActivity.kt
│       │       │   ├── SaudeActivity.kt
│       │       │   ├── ContatosActivity.kt
│       │       │   ├── WelcomeDialogFragment.kt
│       │       │   └── RecordViewModel.kt
│       │       └── adapters/
│       │           ├── RecordAdapter.kt
│       │           ├── HealthUnitAdapter.kt
│       │           └── ContactAdapter.kt
│       └── res/
│           ├── layout/           ← Todos os XMLs de tela
│           ├── drawable/         ← Ícones PNG + backgrounds XML
│           ├── values/           ← colors, strings, themes, dimens
│           ├── font/             ← Montserrat (downloadable)
│           ├── mipmap-*/         ← Ícone do app
│           └── xml/file_paths.xml
├── build.gradle
└── settings.gradle
```

---

## Como abrir no Android Studio

1. **Abra o Android Studio** (Hedgehog 2023.1.1 ou superior)
2. `File → Open` → selecione a pasta `FortalezaApp/`
3. Aguarde o Gradle Sync terminar
4. Se pedir SDK: `Tools → SDK Manager` → instale **Android 14 (API 34)**

---

## Requisitos

| Item | Versão |
|------|--------|
| Android Studio | Hedgehog 2023.1.1+ |
| Kotlin | 1.9.22 |
| Gradle | 8.4 |
| minSdk | 26 (Android 8.0) |
| targetSdk | 34 (Android 14) |
| Java | 17 |

---

## Funcionalidades implementadas

### Home
- Banner completo com a imagem `BBANNER.png`
- 4 cards com ícones PNG reais (transparência)
- Pop-up de boas-vindas ao entrar

### Reclame Aqui
- Categorias via Spinner
- Descrição em campo de texto
- Câmera nativa + galeria
- **Room Database** — registros salvos localmente
- Lista de Meus Registros com status (Em análise / Resolvido)
- Swipe to delete

### IPTU & Impostos
- Situação fiscal do contribuinte
- Botão que abre **portal SEFIN** no navegador
- Emitir boleto externo

### Saúde +
- Agente de saúde com **ligação real** (toque no telefone)
- 3 postos de saúde
- Botão ** Ver no Mapa** → abre Google Maps direto no endereço
- Botão ** Ligar** → abre discador nativo

### Contatos Públicos
- 12 secretarias e serviços
- Busca em tempo real por nome ou categoria
- Botão **Ligar** → abre discador nativo com número

---

## Permissões necessárias

```xml
CALL_PHONE    → ligar para órgãos
CAMERA        → tirar foto de ocorrências
READ_MEDIA_IMAGES → galeria (Android 13+)
INTERNET      → portal SEFIN, Google Maps
```

---

## Design

- Fonte: **Montserrat** (Google Fonts Downloadable)
- Cor primária: `#00897B` (Teal)
- Cor secundária: `#E64A19` (Laranja)
- Cards com `CardView` + sombra
- Telas internas com header verde teal

---

## Banco de dados (Room)

A entidade `Record` persiste localmente:

```kotlin
@Entity(tableName = "records")
data class Record(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val category: String,
    val description: String,
    val photoPath: String? = null,
    val date: String,
    val status: String = "pending"
)
```

---

## Observações

- **Fontes**: na primeira execução com internet, o Android baixa automaticamente a fonte Montserrat via Google Fonts Provider. Sem internet, usa a fonte padrão do sistema.
- **Ícones**: os 4 PNGs (`icon1.png` a `icon4.png`) já estão processados com fundo transparente em `res/drawable/`.
- **Banner**: `banner_home.png` em `res/drawable/`.
- Para **publicar na Play Store**, configure `keystore` em `app/build.gradle`.
