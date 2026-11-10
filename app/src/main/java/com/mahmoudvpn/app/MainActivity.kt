package com.mahmoudvpn.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    private var connected = false
    private lateinit var status: TextView
    private lateinit var connectButton: Button
    private var selectedProtocol = "SSH"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.rgb(13, 10, 20)
        window.navigationBarColor = Color.rgb(13, 10, 20)
        window.decorView.systemUiVisibility = 0

        val page = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(24, 38, 24, 24)
            setBackgroundColor(Color.rgb(13, 10, 20))
        }

        val title = TextView(this).apply {
            text = "MAHMOUD VPN"
            textSize = 28f
            setTextColor(Color.WHITE)
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }
        page.addView(title, matchWrap())

        val subtitle = TextView(this).apply {
            text = "اتصالك، بطريقة أبسط"
            textSize = 15f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 8, 0, 30)
        }
        page.addView(subtitle, matchWrap())

        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(18, 22, 18, 22)
            setBackgroundColor(Color.rgb(31, 23, 45))
        }

        status = TextView(this).apply {
            text = "غير متصل"
            textSize = 18f
            setTextColor(Color.rgb(210, 180, 255))
            gravity = Gravity.CENTER
        }
        card.addView(status, matchWrap())

        val protocolLabel = TextView(this).apply {
            text = "اختر البروتوكول"
            textSize = 14f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            setPadding(0, 24, 0, 12)
        }
        card.addView(protocolLabel, matchWrap())

        val protocols = listOf("SSH", "VLESS", "VMess", "WireGuard")
        val protocolRow = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }
        protocols.forEach { protocol ->
            val b = Button(this).apply {
                text = protocol
                textSize = 11f
                isAllCaps = false
                setTextColor(Color.WHITE)
                setBackgroundColor(if (protocol == selectedProtocol) Color.rgb(125, 55, 190) else Color.rgb(70, 55, 85))
                setOnClickListener {
                    selectedProtocol = protocol
                    status.text = "تم اختيار $protocol"
                    for (i in 0 until protocolRow.childCount) {
                        val child = protocolRow.getChildAt(i) as Button
                        child.setBackgroundColor(
                            if (child.text.toString() == selectedProtocol) Color.rgb(125, 55, 190)
                            else Color.rgb(70, 55, 85)
                        )
                    }
                }
            }
            protocolRow.addView(b, LinearLayout.LayoutParams(0, 48, 1f).apply {
                setMargins(3, 0, 3, 0)
            })
        }
        card.addView(protocolRow, matchWrap())

        connectButton = Button(this).apply {
            text = "اتصال"
            textSize = 18f
            isAllCaps = false
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.rgb(145, 65, 210))
            setOnClickListener {
                connected = !connected
                status.text = if (connected) "وضع تجريبي — غير متصل فعليًا" else "غير متصل"
                text = if (connected) "فصل" else "اتصال"
            }
        }
        card.addView(connectButton, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 58
        ).apply { setMargins(0, 26, 0, 0) })

        page.addView(card, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        ))

        val note = TextView(this).apply {
            text = "نسخة واجهة أولية. يلزم دمج مكتبات البروتوكولات وإعدادات السيرفر قبل تشغيل اتصال VPN حقيقي."
            textSize = 12f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
            setPadding(8, 24, 8, 0)
        }
        page.addView(note, matchWrap())

        setContentView(page)
    }

    private fun matchWrap() = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
}
