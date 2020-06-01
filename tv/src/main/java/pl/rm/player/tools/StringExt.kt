package pl.rm.player.tools


val String.byPrependingImageBasePath: String
    get() = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/$this"
