package com.vivek.quicknews.data.model

data class Category(val name: String, val imageUrl: String)
val categories = listOf(
    Category("Trending","https://media.istockphoto.com/id/1387606908/vector/hot-news-label-with-megaphone-breaking-news-announce-loudspeaker-icon-vector-illustration.jpg?s=612x612&w=0&k=20&c=a7rkv0XkFa5prsiLgx-8o0djSLjVGP-IrBnpnFFnHyw="),
    Category("Sports", "https://thumbs.dreamstime.com/b/sport-news-logo-icon-sports-broadcast-media-symbol-business-broadcasting-channel-live-stream-icons-251944842.jpg"),
    Category("Business", "https://www.shutterstock.com/image-vector/business-news-vector-icon-symbol-260nw-1191051037.jpg"),
    Category("Fashion","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeuYqOsHMB4ArhymR6mkzxVljJ0au3B-QIeg&s"),
    Category("Science","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxXZyXOwnNbCcm4kE7t05gxEF-l2GK8bV4aA&s"),
    Category("Technology", "https://png.pngtree.com/png-vector/20231016/ourmid/pngtree-breaking-technology-news-on-ripped-paper-theme-bad-png-image_10223712.png"),
    Category("Health", "https://cdn.dribbble.com/users/71890/screenshots/2368143/health_news.jpg"),
    Category("Entertainment", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQljaSPUXkeCvdHZ2rTE1DHhXR7gdUP2oLL9g&s"),
)