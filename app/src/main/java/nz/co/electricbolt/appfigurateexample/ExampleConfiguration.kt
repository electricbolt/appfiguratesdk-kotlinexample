// ExampleConfiguration.kt
// AppfigurateExample Copyright © 2022-2025; Electric Bolt Limited.

package nz.co.electricbolt.appfigurateexample

import nz.co.electricbolt.appfiguratelibrary.Configuration
import nz.co.electricbolt.appfiguratelibrary.annotations.ActionMethod
import nz.co.electricbolt.appfiguratelibrary.annotations.BooleanProperty
import nz.co.electricbolt.appfiguratelibrary.annotations.DoublePropertyEdit
import nz.co.electricbolt.appfiguratelibrary.annotations.DoublePropertyList
import nz.co.electricbolt.appfiguratelibrary.annotations.DoublePropertyListEdit
import nz.co.electricbolt.appfiguratelibrary.annotations.DoublePropertySlider
import nz.co.electricbolt.appfiguratelibrary.annotations.EncryptedStringPropertyListEdit
import nz.co.electricbolt.appfiguratelibrary.annotations.FloatPropertyEdit
import nz.co.electricbolt.appfiguratelibrary.annotations.FloatPropertyList
import nz.co.electricbolt.appfiguratelibrary.annotations.FloatPropertyListEdit
import nz.co.electricbolt.appfiguratelibrary.annotations.FloatPropertySlider
import nz.co.electricbolt.appfiguratelibrary.annotations.IconSlider
import nz.co.electricbolt.appfiguratelibrary.annotations.IntPropertyEdit
import nz.co.electricbolt.appfiguratelibrary.annotations.IntPropertyList
import nz.co.electricbolt.appfiguratelibrary.annotations.IntPropertyListEdit
import nz.co.electricbolt.appfiguratelibrary.annotations.IntPropertySlider
import nz.co.electricbolt.appfiguratelibrary.annotations.RemoteBooleanProperty
import nz.co.electricbolt.appfiguratelibrary.annotations.RemoteDoublePropertyEdit
import nz.co.electricbolt.appfiguratelibrary.annotations.RemoteIntPropertyEdit
import nz.co.electricbolt.appfiguratelibrary.annotations.RemoteStringPropertyEdit
import nz.co.electricbolt.appfiguratelibrary.annotations.StringPropertyEdit
import nz.co.electricbolt.appfiguratelibrary.annotations.StringPropertyList
import nz.co.electricbolt.appfiguratelibrary.annotations.StringPropertyListEdit
import java.util.Random

class ExampleConfiguration : Configuration() {

    // region boolean
    @BooleanProperty(description = "boolean - Boolean true or false")
    var bool = false
    // endregion boolean

    // region String
    @StringPropertyEdit(description = "String - textfield", restart = true)
    var string_Textfield: String? = null

    @StringPropertyEdit(description = "String - textfield with regex", regularExpression = "b[aeiou]t")
    var string_RegexTextfield: String? = null

    @StringPropertyList(description = "String - fixed list", keys = [
        "{PVT,DEV}Small",
        "Medium",
        "{TEST}Large"
    ], values = [
        "sm",
        "md",
        "lg"
    ])
    var string_List: String? = null

    @StringPropertyListEdit(description = "String - textfield, customizable list", keys = [
        "{DEV}Red",
        "Green",
        "{pvt,TEST}Blue"
    ], values = [
        "r",
        "g",
        "b"
    ])
    var string_Textfield_List: String? = null

    @StringPropertyListEdit(description = "String - textfield with regex, customizable list",
        regularExpression = "https://[\\w\\.-]+\\.electricbolt.co.nz/.*", keys = [
        "{DEV}Dev",
        "{TEST}Test",
        "{PVT}Prod"
    ], values = [
        "https://dev.electricbolt.co.nz/list",
        "https://test.electricbolt.co.nz/list",
        "https://m.electricbolt.co.nz/list"
    ])
    var string_RegexTextfield_List: String? = null
    // endregion String

    // region Encrypted String
    @EncryptedStringPropertyListEdit(description = "encrypted String - textfield, customizable list", encrypted = BuildConfig.ENCRYPTED)
    var encrypted_string_Textfield_List: String? = null

    companion object {
        fun CONFIGURATION(): ExampleConfiguration {
            return sharedConfiguration() as ExampleConfiguration
        }

        var _encrypted_string_Textfield_ListValues: MutableMap<String, String> = HashMap()
        var _encrypted_string_RegexTextfield_ListValues: MutableMap<String, String> = HashMap()

        init {
            _encrypted_string_Textfield_ListValues["{DEV}Red"] = if (BuildConfig.ENCRYPTED) "3/iZZ1Gv8EniGIkIoUzLdJI8SN8tGjus/jGanA6kRM+afnCJYI4E/6Nc1CWK7eIAJBuZRceU6rtlY/OPTSubV4kLQkf/I4gN4pdrC0" +
                    "T8wZSI6uq7xSWk3nfGG6Ujh5sopOpvseCfg+TaTCfYgEgBwlCuxKZICMNH36BZT41v14foSG8AiGjXVcyQ1eYhY9SxQS" +
                    "d8ZsrQhUyyVcInsuJP3GK20yOg2yQjsbOHD3OfkI+adP5M7Y3B7oC/1sPz+CK2ZNhnOMSTeIl3NpfH6YhSkg2Oh5GTZI" +
                    "2cNAGYxPVTgF6/oCCmky8pgZwQFkh7KKqlKKWEB8Mec5emzr2I3IFF8nNQcsX0ck2sGNClqWXY3ysB" else "r"
            _encrypted_string_Textfield_ListValues["{PVT}Green"] = if (BuildConfig.ENCRYPTED) "PkQfHwaWmK94u9eB9mCfWmOk/vOIOacKivt1lptgJbUzUl2e8pmRnSVLkCeYxoSjPgKEx94t6e/BRhtgMbZyXoo2usUH5jdxaA8Hko" +
                    "UtR4VuMHuZ4Gla+kyuNg1Ki6LC+xAD++dESITzX4EqCCCfVV/qv5u5c9NJYrc7TwNN8bXtVJ6+8hEQeWXdk/05p0wCDj" +
                    "x8hIl+wfmjbKOYwl35mQMW/4EM7SEYFqExxMsR/dcHJG4X0Mw03hEfdqL9nk0anMEyx8ez03WmR8TiZg4UqpbARr6i8I" +
                    "UYx6pMr2ZJjQUzllvTxGpT84VetpN1tvp/Ys+ygtOODZuSp3MjpG0M/tiyGe8g7gNIdauWxN1afdgB" else "g"
            _encrypted_string_Textfield_ListValues["{TEST}Blue"] = if (BuildConfig.ENCRYPTED) "jaIxDwexYEFUlmoG8QsdKYbj/g2zZWL0q5gmeWGCuLOwXvUHdB0CU7i4v/55LEi3UuN7L9Yj4zjWX//WZB0HFn5+9NZ8IG9xoc+uE+" +
                    "vDMbxAKaqjA5IfJM38eVl0DK/hp0le3qOG11sd+GXoSXSDiwKWGoi" + "hqYZ6GUzgDTx8f2OlhqG/SUlm/jADLUNJj" +
                    "K8czZ8fY+Fx75cj7OTlOl+nikFoE5BkR3exOYkeCmAY2KgCYyJQn2w3yaBS0tGafUjAIHIHITodrjG+IbjZLhZyG2/jx" +
                    "kMjLNDs0GCWH6vh+mQ9h6Iwzq3UIZnK9zUn2D4cZV5I2StjPpPdAkGrsdIv0L/bQJZjAnidQIWOC5iB7poB" else "b"
        }

        init {
            _encrypted_string_RegexTextfield_ListValues["{DEV}Dev"] = if (BuildConfig.ENCRYPTED) "jm9SM4MEYa4FRbTrK23n6QWpBl0MfLolPPUrnaUcMcCzmJbzBT0MumGIEFFthMFbx5ZZta8mCIKYwBR2Cu/2b52rFUr2N8xCuWiWUR" +
                    "S0erO6sxdY3bJSzRJPo70aGYzMLydVrxUtHv1BjeLTYaG/25hF5JYWf8/TOA4lBRgMsttwbiO+0D6u1qGh4pewpTD5lI" +
                    "8jK39sY3DfSco7+61chGakI7n44y/+toqxWl8rEa5+I9eL0vDASfe7V2KvBhQGV8FLZDkVo0a0fqcCeq5AXU1bl0bMXI" +
                    "B2anN1PdihbPjOLZgpllIzuY+Hy8gFfpoCGQRAiiPSI2YDxT+pg2fKUBMQ9OqFAC5fi+7DqfRhy6SRXjfemq5cDglwMG" +
                    "npRI4JAQ==" else "https://dev.appfigurate.io/list"
            _encrypted_string_RegexTextfield_ListValues["{TEST}Test"] = if (BuildConfig.ENCRYPTED) "4H4M2YQ2XH+bm7FYVm1sQuN+DaUQdvJecWLKYo3peUYwNJsw+bE/ANFbtfD6xDWCnDW8Bs62tqYDtyg6YhucnTjx82c+axxKG/eDpE" +
                    "d3nPdiWubrw3UwuWa8xKIH8RFVQyRDpWrCbSDG/rREiRdUUs4Lwer7uJfOa92u9iN56T5rgH7/IXfo2N///FxmN/6Tr2" +
                    "vVXNVc+m3snBE8B05+4WUKuDLkjGcm" + "HfzlnRiQVyDDyNKDNr1vDoZduu5Fb5CwPfCrxNIJY4r0XFyPIv+pjAG/Z" +
                    "8Tl2dud6SjCzKirb1Nc0ZeQsCKyhSXDN3PC82qMbviAlkCOvkZ2mca4mRcW1VqeR63Ujp37lCAZKExPdqSlElI4Z0J7/" +
                    "dHuEyMFNP1/xngi3iuOxO6L7Qy9i/RTnQE=" else "https" + "://test.appfigurate.io/list"
            _encrypted_string_RegexTextfield_ListValues["{PVT}Prod"] = if (BuildConfig.ENCRYPTED) "IE7X1bkOQGAqCTtLk011hG0Kmj5l0RkSviSO6IfItnIlSEL66ZH1cO4vmN8FtjkWhD+zvcDzHkcsVSsqPrkmYJl29I2otn2dXa9tNI" +
                    "ZMW3it1kyoyxnZLwzIJhPGORTMxeLN9h+U36lydUA4fASALFFP+uC+bVHVdvIVUMoGBDdoVwZAXyss4C+rgLTafkok4H" +
                    "cz/a6rXC5SsluX8/LTnomluqfi8bgrSS2xJKKpHsQQllQmsshublBJ57bKn3EeKmo6DxwBuwBVA2nAdyoi1bLUYN+XN1" +
                    "sjzLaAuYyZeDJGs9qiuj78HCyN4ZrcVe3x00AbNAlSISrZJrw2oMt3exSGFGKAenClYqwoVygDPIPI3vNFCRFcp29N7T" +
                    "4S78K5AQ==" else "https://m.appfigurate.io/list"
        }
    }

    fun encrypted_string_Textfield_ListValues(): Map<String, String> {
        return _encrypted_string_Textfield_ListValues
    }

    @EncryptedStringPropertyListEdit(description = "encrypted String - textfield with regex, customizable list", regularExpression = "https://[\\w\\.-]+\\.appfigurate.io/.*", encrypted = BuildConfig.ENCRYPTED)
    var encrypted_string_RegexTextfield_List: String? = null
    fun encrypted_string_RegexTextfield_ListValues(): Map<String, String> {
        return _encrypted_string_RegexTextfield_ListValues
    }
    // endregion Encrypted String

    // region int
    @IntPropertySlider(description = "int - slider", minValue = 0, maxValue = 1000, sliderIcon = IconSlider.IconSliderNumeric, restart = true)
    var integer_Slider = 0

    @IntPropertyEdit(description = "int - textfield", minValue = 10, maxValue = 20)
    var integer_Textfield = 0

    @IntPropertyEdit(description = "int - textfield with regex", minValue = 1, maxValue = 999, regularExpression = "^([1-9]|[1-9][0-9]|[1-9][0-9][0-9])$")
    var integer_RegexTextfield = 0

    @IntPropertyList(description = "int - fixed list", keys = [
        "None",
        "{DEV}Low",
        "{PVT}Zero",
        "High",
        "{TEST}Urgent"
    ], values = [
        -100,
        -50,
        0,
        50,
        100
    ])
    var integer_List = 0

    @IntPropertyListEdit(description = "int - textfield, customizable list", minValue = 0, maxValue = 100, keys = ["Failed", "Pass", "Excellence"], values = [0, 80, 90])
    var integer_Textfield_List = 0

    @IntPropertyListEdit(description = "int - textfield with regex, customizable list", minValue = 0, maxValue = 365, regularExpression = "^(0?[0-9]?[0-9]|[1-2][0-9][0-9]|3[0-5][0-9]|36[0-5])$", keys = ["0 days", "1 month", "1 Year"], values = [0, 30, 365])
    var integer_RegexTextfield_List = 0
    // endregion int

    // region float
    @FloatPropertySlider(description = "float - slider", minValue = 0.0f, maxValue = 1000.0f, sliderIcon = IconSlider.IconSliderVolume)
    var float_Slider = 0.0f

    @FloatPropertyEdit(description = "float - textfield", minValue = 10.0f, maxValue = 20.0f, restart = true)
    var float_Textfield = 0.0f

    @FloatPropertyEdit(description = "float - textfield with regex", minValue = 5.0f, maxValue = 250.0f, regularExpression = "^(?:[1-9]\\d*|0)?(?:\\.\\d+)?$")
    var float_RegexTextfield = 0.0f

    @FloatPropertyList(description = "float - fixed list", keys = ["None", "Low", "Zero", "High", "Urgent"], values = [-100.0f, -50.0f, 0.0f, 50.0f, 100.0f])
    var float_List = 0.0f

    @FloatPropertyListEdit(description = "float - textfield, customizable list", minValue = 0.0f, maxValue = 100.0f, keys = ["Failed", "Pass", "Excellence"], values = [0.0f, 80.0f, 90.0f])
    var float_Textfield_List = 0.0f

    @FloatPropertyListEdit(description = "Float - textfield with regex, customizable list", minValue = 0.0f, maxValue = 366.0f, regularExpression = "^(0?[0-9]?[0-9]|[1-2][0-9][0-9]|3[0-5][0-9]|36[0-5])?(?:\\.\\d+)?$", keys = ["0 days", "1 month", "1 Year"], values = [0.0f, 30.0f, 365.0f])
    var float_RegexTextfield_List = 0.0f
    // endregion float

    // region double
    @DoublePropertySlider(description = "double - slider", minValue = 0.0, maxValue = 1000.0, sliderIcon = IconSlider.IconSliderBrightness)
    var double_Slider = 0.0

    @DoublePropertyEdit(description = "double - textfield", minValue = 10.0, maxValue = 20.0)
    var double_Textfield = 0.0

    @DoublePropertyEdit(description = "double - textfield with regex", minValue = 5.0, maxValue = 250.0, regularExpression = "^(?:[1-9]\\d*|0)?(?:\\.\\d+)?$")
    var double_RegexTextfield = 0.0

    @DoublePropertyList(description = "double - fixed list", keys = ["None", "Low", "Zero", "High", "Urgent"], values = [-100.0, -50.0, 0.0, 50.0, 100.0], restart = true)
    var double_List = 0.0

    @DoublePropertyListEdit(description = "double - textfield, customizable list", minValue = 0.0, maxValue = 100.0, keys = ["Failed", "Passed", "Excellence"], values = [0.0, 80.0, 90.0])
    var double_Textfield_List = 0.0

    @DoublePropertyListEdit(description = "double - textfield with regex, customizable list", minValue = 0.0, maxValue = 366.0, regularExpression = "^(0?[0-9]?[0-9]|[1-2][0-9][0-9]|3[0-5][0-9]|36[0-5])?(?:\\.\\d+)?$", keys = ["0 days", "1 month", "1 Year"], values = [0.0, 30.0, 365.0])
    var double_RegexTextfield_List = 0.0
    // endregion double

    // region remote
    @RemoteBooleanProperty(
        propertyKey = "alwaysDarkMode",
        description = "Force dark mode to be always set"
    )
    var alwaysDarkMode: Boolean = false

    @RemoteStringPropertyEdit(propertyKey = "appTitle", description = "Title of application")
    var appTitle: String? = null

    @RemoteDoublePropertyEdit(propertyKey = "fontSize", description = "Size of font throughout app")
    var fontSize: Double = 0.0

    @RemoteIntPropertyEdit(
        propertyKey = "bookingDuration",
        description = "Duration (days) for reservation bookings"
    )
    var bookingDuration: Int = 0
    // end region remote

    // region Action Methods
    @ActionMethod(description = "Set all integer properties to random values", restart = true)
    fun randomIntegers() {
        val random = Random()
        integer_Slider = random.nextInt(1001)
        integer_Textfield = random.nextInt(11) + 10
        integer_RegexTextfield = random.nextInt(999) + 1
        integer_List = when (random.nextInt(5)) {
            0 -> -100
            1 -> -50
            2 -> 0
            3 -> 50
            else -> 100
        }
        integer_Textfield_List = when (random.nextInt(3)) {
            0 -> 0
            1 -> 80
            else -> 90
        }
        integer_RegexTextfield_List = random.nextInt(366)
    }

    @ActionMethod(description = "Reset integer properties to defaults", restart = true)
    fun resetIntegers() {
        integer_Slider = 500
        integer_Textfield = 10
        integer_RegexTextfield = 500
        integer_List = 0
        integer_Textfield_List = 80
        integer_RegexTextfield_List = 30
    }
    // endregion Action Methods

    override fun allowInvalidSignatures(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun publicKey(): String {
        // 41 36 87 71 0D 05
        return """
            -----BEGIN PUBLIC KEY-----
            MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4TZnKfGeXttN7Rr3eiAZ
            PMEPsZvbo7lgIpMh6OjgBsoqkJJP0yXXLtpqsBCv8vm7RYqCn5+yfkiCQiXvkJBz
            FSKmLF9EPR9l1H+32Id82dDuseD70D66puPUHjciEgmU18DpW2NVvTAykMwTEsiR
            0h/ExBEhUe75qtwlVno8cMFbEfVtiGbKECvWIr122ED71T0Jt2Bcxqx1a7c1hPIV
            RwLxIfWfE0+2rB9nJVPBgsTVPywibDvjio82FousyMDmvkAbMq5iyuyvJ0+5bATz
            o12GEt5lSiQlCMzfmkWYBROMDCh27qGFVVo1XAUCVsMfsW9n4iQcoLAdUp/LI3B3
            ywIDAQAB
            -----END PUBLIC KEY-----
            """.trimIndent()
    }

    override fun environmentTags(): Array<String> {
        return arrayOf("Dev", "Test", "PVT")
    }

    override fun reset() {
        // Local properties
        bool = true
        string_Textfield = "tuesday"
        string_RegexTextfield = "bot"
        string_List = "sm"
        string_Textfield_List = "g"
        string_RegexTextfield_List = "https://m.electricbolt.co.nz/list"
        encrypted_string_Textfield_List = "g"
        encrypted_string_RegexTextfield_List = "https://m.appfigurate.io/list"
        integer_Slider = 500
        integer_Textfield = 10
        integer_RegexTextfield = 500
        integer_List = 0
        integer_Textfield_List = 80
        integer_RegexTextfield_List = 30
        float_Slider = 950.0f
        float_Textfield = 12.0f
        float_RegexTextfield = 5.95f
        float_List = -50.0f
        float_Textfield_List = 80.0f
        float_RegexTextfield_List = 365.0f
        double_Slider = 950.0
        double_Textfield = 12.0
        double_RegexTextfield = 5.95
        double_List = -50.0
        double_Textfield_List = 80.0
        double_RegexTextfield_List = 365.0

        // Remote properties
        alwaysDarkMode = true
        appTitle = "Remote Config Test"
        fontSize = 13.0
        bookingDuration = 180
    }
}