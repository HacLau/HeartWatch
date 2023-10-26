package com.shunlin.heartwatch.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.shunlin.heartwatch.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsEntity(
    var title:String,
    var content:String,
    @DrawableRes
    var image:Int,
    var from:String
):Parcelable

val newsImageList:MutableList<Int> = mutableListOf<Int>(
    R.mipmap.ic_news_image_0,
    R.mipmap.ic_news_image_1,
    R.mipmap.ic_news_image_2,
    R.mipmap.ic_news_image_3
)
val news1 = NewsEntity(
    "What is blood pressure?",
    "Blood pressure is the force of blood pushing against the walls of arteries. With each heartbeat, blood is pumped into the arteries. Blood pressure is highest when the heart beats and pumps blood, known as systolic pressure. When your heart is at rest between beats, your blood pressure decreases. This is called diastolic pressure.\nYour blood pressure reading is represented by these two numbers. Typically, the systolic value is higher than or comes before the diastolic value. For example, 120/80 means a systolic pressure of 120 and a diastolic pressure of 80.",
    newsImageList.random(),
    "https://medlineplus.gov/highbloodpressure.html"
)
val news2 = NewsEntity(
    "How is high blood pressure diagnosed?",
    "High blood pressure usually has no symptoms. Therefore the only way to determine whether you have this condition is by getting regular blood pressure checks from your healthcare provider. Your provider will use a gauge a stethoscope or electronic sensor and a blood pressure cuff. Before making a diagnosis he or she will take two or more readings at separate appointments.",
    newsImageList.random(),
    "https://medlineplus.gov/highbloodpressure.html"
)

val news3 = NewsEntity(
    "Types of Hypertension    ",
    "There are two main types of high blood pressure: primary (essential) hypertension and secondary hypertension.\n\nPrimary or essential hypertension is the most common type of high blood pressure. For most people with this type of blood pressure it gradually increases as they age.\n\nSecondary hypertension is caused by another medical condition or the use of certain medications. It usually improves after treating the underlying condition or discontinuing the medications that are causing it.",
    newsImageList.random(),
    "https://medlineplus.gov/highbloodpressure.html"

)
val news4 = NewsEntity(
    "Why Should I Be Concerned About Hypertension?",
    "When your blood pressure remains consistently high over time it forces your heart to work harder to pump blood leading to potential serious health problems such as heart attacks strokes heart failure and kidney failure.",
    newsImageList.random(),
    "https://medlineplus.gov/highbloodpressure.html"
)
val news5 = NewsEntity(
    "How Can I Prevent Hypertension?",
    "Healthy Diet: To help control blood pressure you should limit your intake of sodium (salt) and increase your dietary potassium. Eating low-fat foods and including plenty of fruits vegetables and whole grains is also important. The DASH (Dietary Approaches to Stop Hypertension) eating plan is an example of a diet plan that can help lower blood pressure.\n\nRegular Exercise: Physical activity can help you maintain a healthy weight and lower blood pressure. Aim for at least 2 and a half hours of moderate-intensity aerobic exercise per week or 1 hour and 15 minutes of high-intensity aerobic exercise. Aerobic exercises such as brisk walking involve any activity that raises your heart rate and requires more oxygen than usual.",
    newsImageList.random(),
    "https://medlineplus.gov/howtopreventhighbloodpressure.html"
)
val news6 = NewsEntity(
    "What Is Gestational Hypertension?",
    "Pregnancy-induced hypertension refers to elevated blood pressure that occurs during pregnancy. It typically begins after 20 weeks of pregnancy. You usually don't experience any other symptoms. In many cases it won't harm you or your baby and will resolve within 12 weeks after delivery. However it does increase your risk of future hypertension. Sometimes it can be severe and may lead to low birth weight or preterm birth. Some women with pregnancy-induced hypertension may indeed develop preeclampsia.\n\nChronic hypertension refers to high blood pressure that is present before or before the 20th week of pregnancy. Some women may have had this condition long before pregnancy but it's only discovered during prenatal blood pressure checks. Sometimes chronic hypertension can also lead to preeclampsia.",
    newsImageList.random(),
    "https://medlineplus.gov/highbloodpressureinpregnancy.html"
)
val news7 = NewsEntity(
    "Who Is at Risk for Pre-eclampsia?",
    "You are at risk of developing preeclampsia if you have:\n\n- Pre-existing chronic hypertension or chronic kidney disease before pregnancy\n- Had high blood pressure or preeclampsia in a previous pregnancy\n- Obesity\n- Aged over 40\n- Expecting multiple babies (twins triplets etc.)\n- African American ethnicity\n- A family record of preeclampsia\n- Certain health conditions such as diabetes lupus or a tendency for blood clot formation (a condition that increases the risk of blood clots)\n- Undergoing in vitro fertilization egg donation or donor insemination",
    newsImageList.random(),
    "https://medlineplus.gov/highbloodpressureinpregnancy.html"

)
val news8 = NewsEntity(
    "What Problems Does Pre-eclampsia Cause?",
    "Preeclampsia can lead to various complications including:\n\n- Placental abruption: Separation of the placenta from the uterine wall before delivery\n- Fetal growth restriction: Inadequate supply of nutrients and oxygen to the fetus resulting in poor growth\n- Premature birth: Delivering the baby before 37 weeks of pregnancy\n- Low birth weight baby: The baby is born with a lower weight than expected for their gestational age\n- Stillbirth: The loss of the baby before birth\n- Damage to organs and blood systems including the kidneys liver brain and other organs\n- Increased risk of heart disease for the mother\n- Eclampsia: A severe condition that can occur when preeclampsia affects the brain leading to seizures or coma\n- HELLP syndrome: A rare but serious condition involving damage to the liver and blood cells in women with preeclampsia or eclampsia",
    newsImageList.random(),
    "https://medlineplus.gov/highbloodpressureinpregnancy.html"

)
val news9 = NewsEntity(
    "What Are the Symptoms of Pre-eclampsia?",
    "Preeclampsia can manifest with the following symptoms:\n\n- High blood pressure\n- Excessive protein in urine (known as proteinuria)\n- Swelling of the face and hands. Swelling in the feet might occur as well but it's common in many pregnancies and might not necessarily indicate a problem.\n- Persistent headache\n- Vision disturbances such as blurry vision or seeing spots\n- Pain in the upper right abdomen\n- Shortness of breath",
    newsImageList.random(),
    "https://medlineplus.gov/highbloodpressureinpregnancy.html"

)
val news10 = NewsEntity(
    "How Is Pre-eclampsia Diagnosed?",
    "Delivery typically can cure preeclampsia. When making treatment decisions your provider considers several factors including the severity of the condition how far along you are in your pregnancy and the potential risks to you and your baby:\n\n1. If you are beyond 37 weeks of pregnancy your provider may consider inducing labor or performing a cesarean section.\n\n2. If you are less than 37 weeks pregnant your healthcare provider will closely monitor both you and your baby. This may involve blood and urine tests for you. Monitoring for the baby often includes ultrasounds fetal heart rate monitoring and assessing the baby's growth. You may need medications to control your blood pressure and prevent seizures. Some women might receive steroid injections to help the baby's lungs mature more quickly.",
    newsImageList.random(),
    "https://medlineplus.gov/highbloodpressureinpregnancy.html"

)
val news11 = NewsEntity(
    "DASH Diet Plan",
    "DASH stands for Dietary Approaches to Stop Hypertension. It is a diet plan based on research sponsored by the National Heart Lung and Blood Institute (NHLBI). The studies indicate that DASH can lower high blood pressure and improve cholesterol levels thereby reducing the risk of heart disease.\n\nThe DASH diet plan includes the following principles:\n\n1. Emphasize vegetables fruits and whole grains.\n2. Include low-fat or fat-free dairy products fish poultry legumes nuts and vegetable oils.\n3. Limit foods high in saturated fat. These foods include fatty meats full-fat dairy products and tropical oils like coconut oil palm kernel oil and palm oil.\n4. Restrict sugary beverages and sweets.\n\nIn addition to following the DASH diet other lifestyle changes can also help lower blood pressure. These include maintaining a healthy weight engaging in regular exercise and avoiding smoking.",
    newsImageList.random(),
    "https://medlineplus.gov/dasheatingplan.html"

)
val news12 = NewsEntity(
    "How Is Pre-eclampsia Diagnosed?",
    "Your healthcare provider will check your blood pressure and urine during each prenatal visit. If your blood pressure reading is high (140/90 or higher) especially after the 20th week of pregnancy your provider may want to conduct further tests. These tests might include blood tests and other laboratory examinations to look for excessive protein in your urine and other symptoms.",
    newsImageList.random(),
    "https://medlineplus.gov/highbloodpressureinpregnancy.html"

)

val newsList: MutableList<NewsEntity> = mutableListOf<NewsEntity>(
    news1,
    news2,
    news3,
    news4,
    news5,
    news6,
    news7,
    news8,
    news9,
    news10,
    news11,
    news12
)

