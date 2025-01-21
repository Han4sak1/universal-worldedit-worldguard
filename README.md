# Universal-WorldEdit&WorldGuard

兼容 6 7 并提供一些简单的工具

使用方式:

```kotlin
taboolib{
    relocate("ink.ptms.uw","xx.uw")
}

taboo("ink.ptms:um:1.0.0")
```

## 目前提供以下的跨版本兼容
#### WorldEdit
1. BukkitAdapter
2. ClipBoardFormats
3. ClipBoardHolder & PasteBuilder
#### WorldGuard
1. RegionManager

#### 额外工具
1. FAWE的区块光照刷新工具
2. WorldGuard的区域进入事件
3. WorldEdit选区工具

## 关于FAWE
在FAWE对应WE版本为6.x时基本可支持(建议使用21.03.26, 支持1.8.8-1.12.2)，
为7.x时 尽可能提供主流版本(FAWE现行命名2.8.2以上, MC>=1.16.5)的支持，
其余版本由于FAWE的睿智版本命名不提供支持
