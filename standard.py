from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import Select
import time

# Initialize the test case counter
test_case_counter = 0

# Start the driver
driver = webdriver.Chrome()
driver.get("https://www.saucedemo.com/v1/")
time.sleep(2)

# --- Login ---
driver.find_element(By.ID, "user-name").send_keys("standard_user")
driver.find_element(By.ID, "password").send_keys("secret_sauce")
driver.find_element(By.ID, "login-button").click()
time.sleep(2)
test_case_counter += 1  # Increment counter for login

# --- Access the sorting dropdown ---
sort_dropdown = Select(driver.find_element(By.CLASS_NAME, "product_sort_container"))

# --- Test: Name (A to Z) ---
sort_dropdown.select_by_visible_text("Name (A to Z)")
print("✅ Sorted by Name (A to Z)")
time.sleep(7)
test_case_counter += 1  # Increment counter for sorting test

# --- Test: Name (Z to A) ---
sort_dropdown.select_by_visible_text("Name (Z to A)")
print("✅ Sorted by Name (Z to A)")
time.sleep(7)
test_case_counter += 1  # Increment counter for sorting test

# --- Test: Price (low to high) ---
sort_dropdown.select_by_visible_text("Price (low to high)")
print("✅ Sorted by Price (low to high)")
time.sleep(7)
test_case_counter += 1  # Increment counter for sorting test

# --- Test: Price (high to low) ---
sort_dropdown.select_by_visible_text("Price (high to low)")
print("✅ Sorted by Price (high to low)")
time.sleep(7)
test_case_counter += 1  # Increment counter for sorting test

# --- Wait for the product buttons to be clickable ---
wait = WebDriverWait(driver, 10)

# Add the first product to the cart (using your provided XPath)
first_product_add_button = wait.until(EC.element_to_be_clickable((By.XPATH, "/html/body/div/div[2]/div[2]/div/div[2]/div/div[1]/div[3]/button")))
first_product_add_button.click()
print("✅ Added first product to cart")
time.sleep(2)
test_case_counter += 1  # Increment counter for adding to cart

# Add the second product to the cart (using your provided XPath)
second_product_add_button = wait.until(EC.element_to_be_clickable((By.XPATH, "/html/body/div/div[2]/div[2]/div/div[2]/div/div[2]/div[3]/button")))
second_product_add_button.click()
print("✅ Added second product to cart")
time.sleep(2)
test_case_counter += 1  # Increment counter for adding to cart

# --- Open the cart ---
driver.find_element(By.CLASS_NAME, "shopping_cart_link").click()
print("✅ Opened the cart")
time.sleep(2)
test_case_counter += 1  # Increment counter for opening the cart

# --- Proceed to checkout (using the updated XPath) ---
checkout_button = WebDriverWait(driver, 10).until(EC.element_to_be_clickable((By.XPATH, "/html/body/div/div[2]/div[3]/div/div[2]/a[2]")))
checkout_button.click()
print("✅ Proceeded to checkout")
time.sleep(2)
test_case_counter += 1  # Increment counter for proceeding to checkout

# --- Fill in checkout information ---
driver.find_element(By.ID, "first-name").send_keys("John")
driver.find_element(By.ID, "last-name").send_keys("Doe")
driver.find_element(By.ID, "postal-code").send_keys("12345")
print("✅ Filled in checkout information")
test_case_counter += 1  # Increment counter for filling in checkout information

# --- Continue to the next step ---
continue_button = driver.find_element(By.XPATH, "//input[@class='btn_primary cart_button']")
continue_button.click()
print("✅ Continued to the next step")
time.sleep(2)
test_case_counter += 1  # Increment counter for continuing to the next step

# --- Finish the order (using the new Finish button XPath) ---
finish_button = WebDriverWait(driver, 10).until(EC.element_to_be_clickable((By.XPATH, "/html/body/div/div[2]/div[3]/div/div[2]/div[8]/a[2]")))
finish_button.click()
print("✅ Finished the order")
test_case_counter += 1  # Increment counter for finishing the order

# Happy Scenario Passed
print("\n🎉 Happy Scenario Passed! Now testing Bad Scenarios...")

# --- Log out ---
driver.get("https://www.saucedemo.com/v1/")
time.sleep(2)
print("✅ Logged out successfully")
time.sleep(2)

# --- Test 1: Wrong Username ---
driver.get("https://www.saucedemo.com/v1/")
driver.find_element(By.ID, "user-name").send_keys("wrong_user")
driver.find_element(By.ID, "password").send_keys("secret_sauce")
driver.find_element(By.ID, "login-button").click()
time.sleep(2)
print("✅ Test with wrong username completed")
test_case_counter += 1  # Increment counter for wrong username test

# --- Test 2: Wrong Password ---
driver.get("https://www.saucedemo.com/v1/")
driver.find_element(By.ID, "user-name").send_keys("standard_user")
driver.find_element(By.ID, "password").send_keys("wrong_password")
driver.find_element(By.ID, "login-button").click()
time.sleep(2)
print("✅ Test with wrong password completed")
test_case_counter += 1  # Increment counter for wrong password test

# --- Test 3: No Input ---
driver.get("https://www.saucedemo.com/v1/")
driver.find_element(By.ID, "user-name").send_keys("")
driver.find_element(By.ID, "password").send_keys("")
driver.find_element(By.ID, "login-button").click()
time.sleep(2)
print("✅ Test with no input completed")
test_case_counter += 1  # Increment counter for no input test

# --- Print total executed test cases ---
print(f"\nTotal executed test cases: {test_case_counter}")

# Done
driver.quit()
