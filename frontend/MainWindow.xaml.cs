using System;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;

namespace Frontend
{
    public partial class MainWindow : Window
    {
        private readonly HttpClient _httpClient = new HttpClient();

        public MainWindow()
        {
            InitializeComponent();
        }

        private async void SubmitButton_Click(object sender, RoutedEventArgs e)
        {
            var resume = new
            {
                fullText = FullTextBox.Text == "Full Text" ? null : FullTextBox.Text,
                phone = IncludePhoneCheckbox.IsChecked == true && PhoneBox.Text != "Phone" ? PhoneBox.Text : null,
                email = IncludeEmailCheckbox.IsChecked == true && EmailBox.Text != "Email" ? EmailBox.Text : null,
                sex = SexBox.Text == "Sex" ? null : SexBox.Text,
                experience = IncludeExperienceCheckbox.IsChecked == true && ExperienceBox.Text != "Experience" ? ExperienceBox.Text : null
            };

            var jsonContent = JsonSerializer.Serialize(resume);
            var content = new StringContent(jsonContent, Encoding.UTF8, "application/json");

            try
            {
                var response = await _httpClient.PostAsync("http://localhost:8080/api/resumes", content);

                if (response.IsSuccessStatusCode)
                {
                    var resumeListWindow = new ResumeListWindow();
                    resumeListWindow.Show();
                }
                else
                {
                    MessageBox.Show($"Error: {response.ReasonPhrase}");
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Exception: {ex.Message}");
            }
        }

        private void TextBox_GotFocus(object sender, RoutedEventArgs e)
        {
            var textBox = sender as TextBox;
            if (textBox != null && textBox.Foreground == Brushes.Gray)
            {
                textBox.Text = string.Empty;
                textBox.Foreground = Brushes.Black;
            }
        }

        private void TextBox_LostFocus(object sender, RoutedEventArgs e)
        {
            var textBox = sender as TextBox;
            if (textBox != null && string.IsNullOrWhiteSpace(textBox.Text))
            {
                textBox.Foreground = Brushes.Gray;
                textBox.Text = textBox.Name switch
                {
                    "FullTextBox" => "Full Text",
                    "PhoneBox" => "Phone",
                    "EmailBox" => "Email",
                    "SexBox" => "Sex",
                    "ExperienceBox" => "Experience",
                    _ => textBox.Text
                };
            }
        }
    }
}
